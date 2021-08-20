package com.rup.dit.companyservice.model;

public class Owner {
    private String name;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Owner{");
        sb.append("name='").append(name).append('\'');
        sb.append(", socialSecurityNumber='").append(socialSecurityNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
