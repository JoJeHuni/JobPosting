package com.example.jehunonboarding.controller;

import com.example.jehunonboarding.controller.request.*;
import com.example.jehunonboarding.controller.response.CommonResponse;
import com.example.jehunonboarding.controller.response.JobPostingFindDetailResponse;
import com.example.jehunonboarding.controller.response.JobPostingsSearchResponse;
import com.example.jehunonboarding.domain.JobPosting;
import com.example.jehunonboarding.domain.JobPostingEditInfo;
import com.example.jehunonboarding.domain.JobPostingRemoveInfo;
import com.example.jehunonboarding.domain.JobPostingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JobPostingController {

    private final JobPostingService jobPostingService;

    @PostMapping("/v1/job-postings")
    public ResponseEntity<CommonResponse> register(@Valid @RequestBody JobPostingRegisterRequest request) {
        jobPostingService.register(request.toDomain());
        return new ResponseEntity(new CommonResponse(true), HttpStatus.OK);
    }

    @PutMapping("/v1/job-postings/{jobPostingId}")
    public ResponseEntity<CommonResponse> edit(@PathVariable int jobPostingId, @Valid @RequestBody JobPostingEditInfo request) {
        jobPostingService.edit(jobPostingId, request);
        return new ResponseEntity(new CommonResponse(true), HttpStatus.OK);
    }

    @DeleteMapping("/v1/job-postings/{companyId}/{jobPostingId}")
    public ResponseEntity<CommonResponse> remove(@PathVariable int companyId, @PathVariable int jobPostingId) {
        jobPostingService.remove(companyId, jobPostingId);
        return new ResponseEntity(new CommonResponse(true), HttpStatus.OK);
    }

    @GetMapping("/v1/job-postings")
    public ResponseEntity<JobPostingsSearchResponse> search(String keyword, Pageable pageable) {
        List<JobPosting> jobPostings = jobPostingService.search(pageable, keyword);
        return new ResponseEntity(new JobPostingsSearchResponse(jobPostings), HttpStatus.OK);
    }

    @GetMapping("/v1/job-postings/{jobPostingId}")
    public ResponseEntity<JobPostingFindDetailResponse> findDetail(@PathVariable int jobPostingId) {
        return new ResponseEntity(new JobPostingFindDetailResponse(), HttpStatus.OK);
    }

    @PostMapping("/v1/job-postings/{jobPostingId}/apply")
    public ResponseEntity<CommonResponse> apply(@RequestBody JobPostingApplyRequest request) {
        return new ResponseEntity(new CommonResponse(true), HttpStatus.OK);
    }

}
