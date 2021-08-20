package com.rup.dit.companyservice.model;

import com.rup.dit.companyservice.domain.OwnerDAO;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private String companyName;
    private String country;
    private String phoneNumber;
    private List<Owner> owners;

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
        if(null == owners){
            owners = new ArrayList<>();
        }
        return owners;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Company{");
        sb.append("companyName='").append(companyName).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", owners=").append(owners);
        sb.append('}');
        return sb.toString();
    }
}
