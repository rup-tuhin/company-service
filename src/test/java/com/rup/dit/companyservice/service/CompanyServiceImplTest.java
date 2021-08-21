package com.rup.dit.companyservice.service;

import com.rup.dit.companyservice.domain.CompanyDAO;
import com.rup.dit.companyservice.domain.OwnerDAO;
import com.rup.dit.companyservice.model.Company;
import com.rup.dit.companyservice.model.Owner;
import com.rup.dit.companyservice.repo.CompanyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.Random;

import static com.rup.dit.companyservice.TestData.getCompany;
import static com.rup.dit.companyservice.TestData.getCompanyDAO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceImplTest {

    @Mock
    private CompanyRepository repository;
    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private CompanyServiceImpl service;


    @Test
    public void createCompany() {
        Company company = getCompany();
        CompanyDAO companyDAO = getCompanyDAO();
        when(mapper.map(company, CompanyDAO.class)).thenReturn(companyDAO);
        companyDAO.setCompanyId(new Random().nextLong());
        when(repository.save(any(CompanyDAO.class))).thenReturn(companyDAO);
        when(mapper.map(any(CompanyDAO.class), eq(Company.class))).thenReturn(new Company());
        service.createCompany(company);
        verify(repository).save(any(CompanyDAO.class));
        verify(mapper).map(any(CompanyDAO.class), eq(Company.class));
        verify(mapper, times(2)).map(any(OwnerDAO.class), eq(Owner.class));
    }

    @Test
    public void getAllCompanies() {
        service.getAllCompanies();
    }

    @Test
    public void getCompanyDetails() {
    }

    @Test
    public void updateCompany() {
    }

    @Test
    public void addOwner() {
    }

    @Test
    public void checkSocialSecurityNumber() {
    }
}