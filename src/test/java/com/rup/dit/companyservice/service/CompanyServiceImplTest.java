package com.rup.dit.companyservice.service;

import com.rup.dit.companyservice.domain.CompanyDAO;
import com.rup.dit.companyservice.domain.OwnerDAO;
import com.rup.dit.companyservice.model.Company;
import com.rup.dit.companyservice.model.Owner;
import com.rup.dit.companyservice.repo.CompanyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.Optional;
import java.util.Random;

import static com.rup.dit.companyservice.TestData.getCompany;
import static com.rup.dit.companyservice.TestData.getCompanyDAO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
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
        verify(repository).findAll();
    }

    @Test
    public void getCompanyDetails() {
        Long testCompanyID = new Random().nextLong();
        when(repository.findById(anyLong())).thenReturn(Optional.of(getCompanyDAO()));
        when(mapper.map(any(CompanyDAO.class), eq(Company.class))).thenReturn(new Company());
        service.getCompanyDetails(testCompanyID);
        verify(mapper).map(any(CompanyDAO.class), eq(Company.class));
        verify(mapper, times(2)).map(any(OwnerDAO.class), eq(Owner.class));
    }

    @Test
    public void updateCompany() {
        CompanyDAO companyDAO = new CompanyDAO();
        companyDAO.setCompanyName("updated company");
        companyDAO.setCountry("usa");
        companyDAO.setPhoneNumber("98765");

        when(mapper.map(any(Company.class), eq(CompanyDAO.class))).thenReturn(companyDAO);
        CompanyDAO existingRecord = new CompanyDAO();
        existingRecord.setCompanyId(new Random().nextLong());
        when(repository.findOne(any())).thenReturn(Optional.of(existingRecord));
        service.updateCompany(new Company());
        ArgumentCaptor<CompanyDAO> reqToBeSaved = ArgumentCaptor.forClass(CompanyDAO.class);
        verify(repository).save(reqToBeSaved.capture());
        assertEquals(existingRecord.getCompanyId(), reqToBeSaved.getValue().getCompanyId());
        assertEquals(companyDAO.getCompanyName(), reqToBeSaved.getValue().getCompanyName());
        assertEquals(companyDAO.getCountry(), reqToBeSaved.getValue().getCountry());
        assertEquals(companyDAO.getPhoneNumber(), reqToBeSaved.getValue().getPhoneNumber());

    }

    @Test
    public void addOwner() {
        Long companyId = new Random().nextLong();
        Owner owner = new Owner();
        owner.setName("Add me");
        owner.setSocialSecurityNumber("SS1122");
        when(repository.findById(anyLong())).thenReturn(Optional.of(getCompanyDAO()));
        service.addOwner(companyId, owner);
        verify(mapper).map(any(Owner.class), eq(OwnerDAO.class));
        ArgumentCaptor<CompanyDAO> reqToBeSaved = ArgumentCaptor.forClass(CompanyDAO.class);
        verify(repository).save(reqToBeSaved.capture());
        assertEquals(3, reqToBeSaved.getValue().getOwners().size());
    }

    @Test
    public void checkSocialSecurityNumber() {
        String result = service.checkSocialSecurityNumber(anyString());
        assertTrue("valid".equals(result) || "invalid".equals(result));
    }
}