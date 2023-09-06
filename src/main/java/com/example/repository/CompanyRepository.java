package com.example.jehunonboarding.repository;

import com.example.jehunonboarding.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
