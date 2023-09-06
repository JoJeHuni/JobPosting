package com.example.domain;

import javax.persistence.*;
import javax.validation.Valid;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "job_posting")
@NoArgsConstructor
public class JobPostingEntity {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private int companyId;

    @Column(nullable = false)
    private String jobPosition;

    @Column(nullable = false)
    private long jobCompensation;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String skill;

    @ElementCollection
    private Set<String> applicants = new HashSet<>();

    public JobPostingEntity(int companyId, String jobPosition, long jobCompensation, String description, String skill) {
        this.companyId = companyId;
        this.jobPosition = jobPosition;
        this.jobCompensation = jobCompensation;
        this.description = description;
        this.skill = skill;
    }

    public void editJobPosting(JobPostingEditInfo editInfo) {
        this.jobPosition = editInfo.getJobPosition();
        this.jobCompensation = editInfo.getJobCompensation();
        this.description = editInfo.getDescription();
        this.skill = editInfo.getSkill();
    }

    public boolean removeJobPosting(int companyId, int jobPostingId) {
        return this.companyId == companyId && this.id == jobPostingId;
    }

    public boolean isApplicant(String userId) {
        return applicants.contains(userId);
    }

    public void addApplicant(int userId) {
        applicants.add(String.valueOf(userId));
    }

    public static int getJobPostingId(int integer) {
        return Math.toIntExact(integer);
    }
}
