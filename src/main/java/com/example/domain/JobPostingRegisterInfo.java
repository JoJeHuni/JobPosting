package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JobPostingRegisterInfo {
    private int companyId;
    private String jobPosition;
    private long jobCompensation;
    private String description;
    private String skill;
}
