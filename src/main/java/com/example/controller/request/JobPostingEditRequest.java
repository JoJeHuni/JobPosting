package com.example.controller.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class JobPostingEditRequest {
    @NotNull
    private int companyId;
    @NotNull
    private String jobPosition;
    @NotNull
    private long jobCompensation;
    @NotNull
    private String description;
    @NotNull
    private String skill;
}
