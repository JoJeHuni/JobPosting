package com.example.jehunonboarding.controller.response;

import lombok.Data;

@Data
public class JobPostingFindDetailResponse {

    private int companyId;
    private String companyName;
    private String nation;
    private String region;
    private String jobPosition;
    private long jobCompensation;
    private String description;
    private String skill;
}
