package com.will.caleb.business.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.*;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
@FilterDef(name = "empresaFilter", parameters = @ParamDef(name = "empresaId", type = Integer.class))
@Filter(name = "empresaFilter", condition = "enterprise_id = :empresaId")
public abstract class AbstractEntity extends AbstractGenericEntity{

    @Column(name = "enterprise_id")
    private Integer enterprise;

}
