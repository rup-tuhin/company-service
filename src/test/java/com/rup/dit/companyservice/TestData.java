package com.rup.dit.companyservice;

import com.rup.dit.companyservice.domain.CompanyDAO;
import com.rup.dit.companyservice.domain.OwnerDAO;
import com.rup.dit.companyservice.model.Company;
import com.rup.dit.companyservice.model.Owner;
import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestData {
    public static CompanyDAO getCompanyDAO() {
        OwnerDAO owner1 = new OwnerDAO();
        owner1.setName("Test Owner 1");
        owner1.setSocialSecurityNumber("123123");

        OwnerDAO owner2 = new OwnerDAO();
        owner2.setName("Test Owner 2");
        owner2.setSocialSecurityNumber("456456");

        CompanyDAO company = new CompanyDAO();
        company.setCompanyName("test company");
        company.setCountry("IN");
        company.setPhoneNumber("123456");
        company.setOwners(Lists.newArrayList(owner1, owner2));

        return company;
    }

    public static Company getCompany() {
        Owner owner1 = new Owner();
        owner1.setName("Test Owner 1");
        owner1.setSocialSecurityNumber("123123");

        Owner owner2 = new Owner();
        owner2.setName("Test Owner 2");
        owner2.setSocialSecurityNumber("456456");

        Company company = new Company();
        company.setCompanyName("test company");
        company.setCountry("IN");
        company.setPhoneNumber("123456");
        company.getOwners().add(owner1);
        company.getOwners().add(owner2);

        return company;
    }
}
