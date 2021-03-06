package com.rup.dit.companyservice.repo;

import com.rup.dit.companyservice.domain.CompanyDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static com.rup.dit.companyservice.TestData.getCompanyDAO;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CompanyRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CompanyRepository testRepo;

    @Test
    public void shouldCreateCompany() {
        CompanyDAO company = new CompanyDAO();
        company.setCompanyName("test company");
        company = entityManager.persistAndFlush(company);
        assertEquals(company, testRepo.findById(company.getCompanyId()).orElse(null));
    }

    @Test
    public void shouldReadCompany() {
        CompanyDAO company = getCompanyDAO();
        assertNull(company.getCompanyId());
        company.getOwners().forEach(o -> assertNull(o.getOwnerId()));
        entityManager.persistAndFlush(company);
        CompanyDAO savedRecord = testRepo.findById(company.getCompanyId()).orElse(null);
        assertNotNull(savedRecord);
        assertNotNull(savedRecord.getCompanyId());
        assertEquals("test company", savedRecord.getCompanyName());
        assertEquals("IN", savedRecord.getCountry());
        assertEquals("123456", savedRecord.getPhoneNumber());
        assertNotNull(savedRecord.getOwners());
        assertEquals(2, savedRecord.getOwners().size());
        assertNotNull(savedRecord.getOwners().get(0).getOwnerId());
        assertEquals("Test Owner 1", savedRecord.getOwners().get(0).getName());
        assertEquals("123123", savedRecord.getOwners().get(0).getSocialSecurityNumber());
        assertNotNull(savedRecord.getOwners().get(1).getOwnerId());
        assertEquals("Test Owner 2", savedRecord.getOwners().get(1).getName());
        assertEquals("456456", savedRecord.getOwners().get(1).getSocialSecurityNumber());
    }

    @Test
    public void shouldUpdateCompany() {
        CompanyDAO company = getCompanyDAO();
        assertNull(company.getCompanyId());
        company.getOwners().forEach(o -> assertNull(o.getOwnerId()));
        CompanyDAO savedRecord = entityManager.persistAndFlush(company);

        savedRecord.setCompanyName("updated company");
        savedRecord.setCountry("US");
        savedRecord.setPhoneNumber("98765");
        savedRecord.getOwners().get(0).setName("Updated Owner 1");
        savedRecord.getOwners().get(0).setSocialSecurityNumber("321321");
        savedRecord.getOwners().get(1).setName("Updated Owner 2");
        savedRecord.getOwners().get(1).setSocialSecurityNumber("654654");

        entityManager.persistAndFlush(savedRecord);

        CompanyDAO updatedRecord = testRepo.findById(company.getCompanyId()).orElse(null);
        assertNotNull(updatedRecord);
        assertNotNull(updatedRecord.getCompanyId());
        assertEquals("updated company", updatedRecord.getCompanyName());
        assertEquals("US", updatedRecord.getCountry());
        assertEquals("98765", updatedRecord.getPhoneNumber());
        assertNotNull(updatedRecord.getOwners());
        assertEquals(2, updatedRecord.getOwners().size());
        assertNotNull(updatedRecord.getOwners().get(0).getOwnerId());
        assertEquals("Updated Owner 1", updatedRecord.getOwners().get(0).getName());
        assertEquals("321321", updatedRecord.getOwners().get(0).getSocialSecurityNumber());
        assertNotNull(updatedRecord.getOwners().get(1).getOwnerId());
        assertEquals("Updated Owner 2", updatedRecord.getOwners().get(1).getName());
        assertEquals("654654", updatedRecord.getOwners().get(1).getSocialSecurityNumber());
    }

    @Test
    public void shouldDeleteCompany() {
        CompanyDAO company = getCompanyDAO();
        Long savedRecId = entityManager.persistAndFlush(company).getCompanyId();
        testRepo.deleteById(savedRecId);
        assertNull(testRepo.findById(savedRecId).orElse(null));
    }


}