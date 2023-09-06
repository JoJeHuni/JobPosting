package com.example.controller.response;

import com.example.domain.JobPosting;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class JobPostingsSearchResponse {
    private List<JobPostingResponse> items;

    public JobPostingsSearchResponse(List<JobPosting> jobPostings) {
        this.items = jobPostings.stream()
                .map(JobPostingResponse::new)
                .collect(Collectors.toList());
    }

    @Data
    static class JobPostingResponse {
        private int companyId;
        private String companyName;
        private String nation;
        private String region;
        private String jobPosition;
        private long jobCompensation;
        private String skill;
        
        public JobPostingResponse(JobPosting jobPosting) {
            this.companyId = jobPosting.getCompanyId();
            this.companyName = jobPosting.getCompanyName();
            this.nation = jobPosting.getNation();
            this.region = jobPosting.getRegion();
            this.jobPosition = jobPosting.getJobPosition();
            this.jobCompensation = jobPosting.getJobCompensation();
            this.skill = jobPosting.getSkill();
        }
    }
}


