package com.will.caleb.business.model.entity;

import com.will.caleb.business.pattern.enums.EnumRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "bus_role")
public class Role extends AbstractGenericEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private EnumRole name;
}
