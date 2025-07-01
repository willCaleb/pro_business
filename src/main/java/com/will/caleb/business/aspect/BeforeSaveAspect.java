package com.will.caleb.business.aspect;


import com.will.caleb.business.exception.CustomException;
import com.will.caleb.business.exception.EnumCustomException;
import com.will.caleb.business.model.entity.AbstractEntity;
import com.will.caleb.business.model.entity.Enterprise;
import com.will.caleb.business.service.EnterpriseService;
import com.will.caleb.business.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Collection;

@Aspect
@Component
@RequiredArgsConstructor
public class BeforeSaveAspect {

    private final EnterpriseService enterpriseService;

    @Before("execution(* org.springframework.data.repository.CrudRepository+.save(..)) || " +
            "execution(* org.springframework.data.jpa.repository.JpaRepository+.save(..))")
    public void beforeSave(JoinPoint joinPoint) {

        Object entity = joinPoint.getArgs()[0];

        if (Utils.isNotEmpty(entity) && entity instanceof AbstractEntity) {
            Integer enterpriseId = enterpriseService.getByContext().getId();
            applyEnterpriseId(entity, enterpriseId);
        }
    }

    private void applyEnterpriseId(Object entity, Integer enterpriseId) {
        if (entity instanceof AbstractEntity abstractEntity) {
            abstractEntity.setEnterprise(enterpriseId);

            Field[] fields = entity.getClass().getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(entity);
                    
                    if (value instanceof AbstractEntity child) {
                        applyEnterpriseId(child, enterpriseId);
                    } else if (value instanceof Collection<?> collection) {
                        for (Object item : collection) {
                            if (item instanceof AbstractEntity itemEntity) {
                                applyEnterpriseId(itemEntity, enterpriseId);
                            }
                        }
                    }
                }  catch (Exception e) {
                    LoggerFactory.getLogger(this.getClass()).error("Erro ao salvar empresa em entidade: {} erro: {}", entity.getClass().getSimpleName(), e.getMessage());
                }
            }
        }
    }
}


