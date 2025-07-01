package com.will.caleb.business.model.records.converters;

import com.will.caleb.business.model.entity.AbstractEntity;
import com.will.caleb.business.model.entity.AbstractGenericEntity;
import com.will.caleb.business.model.records.AbstractDTO;
import com.will.caleb.business.utils.ClassUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.RecordComponent;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public abstract class GenericConverter {

    public static <E extends AbstractGenericEntity, DTO extends AbstractDTO> E toEntity(DTO dto, Class<E> entityClass) {
        E entity = ClassUtil.newInstance(entityClass);

        Map<String, Object> dtoFieldValues = getDtoFieldValues(dto);

        defineValuesToEntity(entityClass, dtoFieldValues, entity);

        return entity;
    }

    private static <E extends AbstractGenericEntity> void defineValuesToEntity(Class<?> entityClass, Map<String, Object> dtoFieldValues, E entity) {
        for (Field field : entityClass.getDeclaredFields()) {
            field.setAccessible(true);

            Object value = dtoFieldValues.get(field.getName());
            if (value != null) {
                try {
                    if (value instanceof AbstractDTO) {
                        AbstractGenericEntity nestedEntity = (AbstractGenericEntity) field.get(entity);

                        if (nestedEntity == null) {
                            nestedEntity = (AbstractGenericEntity) field.getType().getDeclaredConstructor().newInstance();
                            field.set(entity, nestedEntity);
                        }

                        Map<String, Object> innerObjectFieldValues = getDtoFieldValues((AbstractDTO) value);
                        defineValuesToEntity(nestedEntity.getClass(), innerObjectFieldValues, nestedEntity);
                    } else {
                        field.set(entity, value);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static <DTO extends AbstractDTO> Map<String, Object> getDtoFieldValues(DTO dto) {
        return Arrays.stream(dto.getClass().getRecordComponents())
                .map(component -> {
                    try {
                        Object value = component.getAccessor().invoke(dto);
                        return new AbstractMap.SimpleEntry<>(component.getName(), value);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .filter(entry -> entry.getValue() != null) // ignora campos com valor null
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }



    public static <E extends AbstractGenericEntity, DTO extends AbstractDTO> DTO toResponse(E entity, Class<DTO> dtoClass) {
        try {
            RecordComponent[] components = dtoClass.getRecordComponents();
            Object[] args = new Object[components.length];

            for (int i = 0; i < components.length; i++) {
                RecordComponent component = components[i];
                String name = component.getName();

                Field field = getField(entity.getClass(), name);
                if (field != null) {
                    field.setAccessible(true);
                    Object value = field.get(entity);

                    if (value instanceof AbstractEntity subEntity && AbstractDTO.class.isAssignableFrom(component.getType())) {
                        args[i] = toResponse(subEntity, (Class<? extends AbstractDTO>) component.getType());
                    } else {
                        args[i] = convertValue(field.get(entity), component.getType());
                    }
                }
            }

            Constructor<DTO> constructor = dtoClass.getDeclaredConstructor(Arrays.stream(components).map(RecordComponent::getType).toArray(Class[]::new));
            return constructor.newInstance(args);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter entidade para DTO", e);
        }
    }

    private static Field getField(Class<?> clazz, String name) {
        try {
            return clazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            if (clazz.getSuperclass() != null) {
                return getField(clazz.getSuperclass(), name);
            }
            return null;
        }
    }
    private static Object convertValue(Object value, Class<?> targetType) {
        return switch (value) {
            case null -> null;
            case Timestamp timestamp when targetType.equals(LocalDateTime.class) -> timestamp.toLocalDateTime();
            case java.sql.Date date when targetType.equals(LocalDate.class) -> date.toLocalDate();
            case Date date when targetType.equals(LocalDateTime.class) ->
                    date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            default -> value;
        };
    }
}
