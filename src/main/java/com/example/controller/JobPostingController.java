package com.example.controller;

import com.example.controller.request.JobPostingApplyRequest;
import com.example.controller.request.JobPostingRegisterRequest;
import com.example.controller.response.CommonResponse;
import com.example.controller.response.JobPostingFindDetailResponse;
import com.example.controller.response.JobPostingsSearchResponse;
import com.example.domain.JobPosting;
import com.example.domain.JobPostingEditInfo;
import com.example.domain.JobPostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        JobPosting jobPosting = jobPostingService.findDetail(jobPostingId).get(0);
        List<Integer> otherJobPostings = jobPostingService.findOtherJobPostings(jobPosting.getCompanyId(), jobPostingId);

        JobPostingFindDetailResponse response = new JobPostingFindDetailResponse(jobPosting, otherJobPostings);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/v1/job-postings/{jobPostingId}/apply")
    public ResponseEntity<CommonResponse> apply(@PathVariable int jobPostingId, @RequestBody JobPostingApplyRequest request) {
        jobPostingService.apply(jobPostingId, request);
        return new ResponseEntity(new CommonResponse(true), HttpStatus.OK);
    }

}
