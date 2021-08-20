package com.rup.dit.companyservice.service;

import com.rup.dit.companyservice.model.Company;
import com.rup.dit.companyservice.model.Owner;

import java.util.List;

public interface CompanyService {
    public Company createCompany(Company company);
    public List<Company> getAllCompanies();
    public Company getCompanyDetails(Long companyId);
    public Company updateCompany(Company company);
    public Company addOwner(Long companyId, Owner owner);
    public String checkSocialSecurityNumber(String socialSecurityNumber);
}