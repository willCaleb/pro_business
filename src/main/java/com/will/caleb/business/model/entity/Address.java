package com.will.caleb.business.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bus_address")
public class Address extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "number")
    private Integer number;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "complement")
    private String complement;

}
