package com.rup.dit.companyservice.domain;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "owners")
public class OwnerDAO implements Serializable {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "company_gen")
    @SequenceGenerator(name = "company_gen", sequenceName = "company_seq")
    private Long ownerId;
    @Column
    private String name;
    @Column
    private String socialSecurityNumber;

    public Long getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }
}
