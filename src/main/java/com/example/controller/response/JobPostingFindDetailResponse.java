package com.example.controller.response;

import com.example.domain.JobPosting;
import lombok.Data;

import java.util.List;

@Data
public class JobPostingFindDetailResponse {

    private JobPostingResponse details;

    public JobPostingFindDetailResponse(JobPosting jobPosting, List<Integer> otherJobPostings) {
        this.details = new JobPostingResponse(jobPosting);
        this.details.setOtherJobPostings(otherJobPostings);
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
        private List<Integer> otherJobPostings;

        public JobPostingResponse(JobPosting jobPosting) {
            this.companyId = jobPosting.getCompanyId();
            this.companyName = jobPosting.getCompanyName();
            this.nation = jobPosting.getNation();
            this.region = jobPosting.getRegion();
            this.jobPosition = jobPosting.getJobPosition();
            this.jobCompensation = jobPosting.getJobCompensation();
            this.skill = jobPosting.getSkill();
        }

        public void setOtherJobPostings(List<Integer> otherJobPostings) {
            this.otherJobPostings = otherJobPostings;
        }
    }
}