package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JobPosting {
    private int companyId;
    private String companyName;
    private String nation;
    private String region;
    private String jobPosition;
    private long jobCompensation;
    private String description;
    private String skill;
}
