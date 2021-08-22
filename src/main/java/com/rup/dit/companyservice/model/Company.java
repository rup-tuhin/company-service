package com.rup.dit.companyservice.model;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.ArrayList;
import java.util.List;

public class Company {
    @JsonView({View.User.class, View.Admin.class})
    private Long id;
    @JsonView({View.User.class, View.Admin.class})
    private String companyName;
    @JsonView({View.User.class, View.Admin.class})
    private String country;
    @JsonView({View.User.class, View.Admin.class})
    private String phoneNumber;
    @JsonView({View.User.class, View.Admin.class})
    private List<Owner> owners;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Owner> getOwners() {
        if (null == owners) {
            owners = new ArrayList<>();
        }
        return owners;
    }

}
