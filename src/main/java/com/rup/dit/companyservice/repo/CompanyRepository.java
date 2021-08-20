package com.rup.dit.companyservice.repo;

import com.rup.dit.companyservice.domain.CompanyDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyDAO, Long> {
}
