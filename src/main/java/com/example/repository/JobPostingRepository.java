package com.example.jehunonboarding.repository;

import com.example.jehunonboarding.domain.JobPosting;
import com.example.jehunonboarding.domain.JobPostingEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobPostingRepository extends JpaRepository<JobPostingEntity, Long> {

    @Query("SELECT new com.example.jehunonboarding.domain.JobPosting(c.id, c.companyName, c.nation, c.region, jp.jobPosition, jp.jobCompensation, jp.description, jp.skill) FROM JobPostingEntity jp INNER JOIN Company c ON jp.companyId = c.id WHERE jp.jobPosition LIKE %:keyword% OR c.companyName LIKE %:keyword%")
    List<JobPosting> findByKeyword(Pageable pageable, String keyword);
}
