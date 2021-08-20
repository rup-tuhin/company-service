package com.rup.dit.companyservice.service;

import com.rup.dit.companyservice.domain.CompanyDAO;
import com.rup.dit.companyservice.domain.OwnerDAO;
import com.rup.dit.companyservice.model.Company;
import com.rup.dit.companyservice.model.Owner;
import com.rup.dit.companyservice.repo.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    @Transactional
    public Company createCompany(Company company) {
        CompanyDAO toBeSaved = mapper.map(company, CompanyDAO.class);
        CompanyDAO savedRecord = companyRepository.saveAndFlush(toBeSaved);
        return mapper.map(savedRecord, Company.class);

    }

    @Override
    @Transactional
    public List<Company> getAllCompanies() {
        List<CompanyDAO> records = companyRepository.findAll();
        records.forEach(CompanyDAO::getOwners);
        return records.stream()
                .map(r->mapper.map(r, Company.class))
                .collect(Collectors.toList());
    }

    @Override
    public Company getCompanyDetails(Long companyId) {
        CompanyDAO record = companyRepository.findById(companyId).orElse(null);
        if(record != null) {
            return mapper.map(record, Company.class);
        }
        return null;
    }

    @Override
    public Company updateCompany(Company company) {
        CompanyDAO example = mapper.map(company, CompanyDAO.class);
        CompanyDAO record = companyRepository.findOne(Example.of(example)).orElse(null);
        if(record != null){
            example.setCompanyId(record.getCompanyId());
            CompanyDAO savedRecord = companyRepository.saveAndFlush(example);
            return mapper.map(savedRecord, Company.class);
        }
        return null;
    }

    @Override
    public Company addOwner(Long companyId, Owner owner) {
        CompanyDAO record = companyRepository.findById(companyId).orElse(null);
        if(record != null){
            record.getOwners().add(mapper.map(owner, OwnerDAO.class));
            CompanyDAO savedRecord = companyRepository.saveAndFlush(record);
            return mapper.map(savedRecord, Company.class);
        }
        return null;
    }

    @Override
    public String checkSocialSecurityNumber(String socialSecurityNumber) {
        return new Random().nextBoolean() ? "valid" : "invalid";
    }
}
