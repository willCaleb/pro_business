package com.will.caleb.business.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bus_client")
public class Client extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday_date")
    private Date birthdayDate;

    @Column(name = "document")
    private String document;

    @Column(name = "inclusion_date")
    private Date inclusionDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "edit_date")
    private Date editDate;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

}
