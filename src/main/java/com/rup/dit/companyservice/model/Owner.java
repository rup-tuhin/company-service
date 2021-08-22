package com.rup.dit.companyservice.model;

import com.fasterxml.jackson.annotation.JsonView;

public class Owner {
    @JsonView({View.User.class, View.Admin.class})
    private String name;
    @JsonView(View.Admin.class)
    private String socialSecurityNumber;

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
