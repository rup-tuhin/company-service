package com.rup.dit.companyservice.controller;

import com.rup.dit.companyservice.model.Company;
import com.rup.dit.companyservice.model.Owner;
import com.rup.dit.companyservice.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("company")
public class CompanyController {

    @Autowired
    private CompanyService service;

    @PostMapping
    public Company createCompany(@RequestBody Company company) {
        //TODO: input validation and security checks.
        return service.createCompany(company);
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return service.getAllCompanies();
    }

    @GetMapping(path = "{id}")
    public Company getCompanyDetails(@PathVariable(name = "id") Long companyId) {
        return service.getCompanyDetails(companyId);
    }

    @PutMapping(path = "{id}")
    public Company updateCompany(@RequestBody Company company, @PathVariable(name = "id") Long companyId) {
        //TODO: input validation and security checks.
        return service.updateCompany(company, companyId);
    }

    @PostMapping(path = "{id}/owner")
    public Company addOwners(@PathVariable(name = "id") Long companyId,
                             @RequestBody Owner owner) {
        //TODO: input validation and security checks.
        return service.addOwner(companyId, owner);
    }

    @GetMapping(path = "{id}/owner/validate")
    public String checkSocialSecurityNumber(@RequestParam(name = "ssNumber") String socialSecurityNumber) {
        return service.checkSocialSecurityNumber(socialSecurityNumber);
    }

}
