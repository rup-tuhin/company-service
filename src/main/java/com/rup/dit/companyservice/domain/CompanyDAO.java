package com.rup.dit.companyservice.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "company")
public class CompanyDAO implements Serializable {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "company_gen")
    @SequenceGenerator(name = "company_gen", sequenceName = "company_seq")
    private Long companyId;
    @Column
    private String companyName;
    @Column
    private String country;
    @Column
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OwnerDAO> owners;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<OwnerDAO> getOwners() {
        return owners;
    }

    public void setOwners(List<OwnerDAO> owners) {
        this.owners = owners;
    }
}
