package com.rup.dit.companyservice.service;

import com.rup.dit.companyservice.domain.CompanyDAO;
import com.rup.dit.companyservice.domain.OwnerDAO;
import com.rup.dit.companyservice.model.Company;
import com.rup.dit.companyservice.model.Owner;
import com.rup.dit.companyservice.repo.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Company createCompany(Company company) {
        CompanyDAO toBeSaved = mapper.map(company, CompanyDAO.class);
        CompanyDAO savedRecord = companyRepository.save(toBeSaved);
        return extractModelFromDAO(savedRecord);
    }


    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map(this::extractModelFromDAO)
                .collect(Collectors.toList());
    }

    @Override
    public Company getCompanyDetails(Long companyId) {
        CompanyDAO record = companyRepository.findById(companyId).orElse(null);
        if (record != null) {
            return extractModelFromDAO(record);
        }
        return null;
    }

    @Override
    public Company updateCompany(Company company, Long companyId) {
        CompanyDAO newRecord = mapper.map(company, CompanyDAO.class);
        CompanyDAO oldRecord = companyRepository.findById(companyId).orElse(null);
        if (oldRecord != null) {
            newRecord.setCompanyId(oldRecord.getCompanyId());
            CompanyDAO savedRecord = companyRepository.save(newRecord);
            return extractModelFromDAO(savedRecord);
        }
        return null;
    }

    @Override
    public Company addOwner(Long companyId, Owner owner) {
        CompanyDAO record = companyRepository.findById(companyId).orElse(null);
        if (record != null) {
            record.getOwners().add(mapper.map(owner, OwnerDAO.class));
            CompanyDAO savedRecord = companyRepository.save(record);
            return extractModelFromDAO(savedRecord);
        }
        return null;
    }

    @Override
    public String checkSocialSecurityNumber(String socialSecurityNumber) {
        return new Random().nextBoolean() ? "valid" : "invalid";
    }


    private Company extractModelFromDAO(CompanyDAO savedRecord) {
        Company result = mapper.map(savedRecord, Company.class);
        if (result != null) {
            result.getOwners()
                    .addAll(savedRecord.getOwners().stream()
                            .map(o -> mapper.map(o, Owner.class))
                            .collect(Collectors.toList()));
            // setting up id in model obj - to avoid DB id manipulation.
            result.setId(savedRecord.getCompanyId());
        }
        return result;
    }
}
