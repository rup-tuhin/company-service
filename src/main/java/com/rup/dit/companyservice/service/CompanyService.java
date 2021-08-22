package com.rup.dit.companyservice.service;

import com.rup.dit.companyservice.model.Company;
import com.rup.dit.companyservice.model.Owner;

import java.util.List;

public interface CompanyService {
    Company createCompany(Company company);

    List<Company> getAllCompanies();

    Company getCompanyDetails(Long companyId);

    Company updateCompany(Company company, Long companyId);

    Company addOwner(Long companyId, Owner owner);

    String checkSocialSecurityNumber(String socialSecurityNumber);
}