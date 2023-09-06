package com.example.domain;

import com.example.controller.request.JobPostingApplyRequest;
import com.example.repository.CompanyRepository;
import com.example.repository.JobPostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;
    private final CompanyRepository companyRepository;

    public void register(JobPostingRegisterInfo registerInfo) {
        companyRepository.findById(registerInfo.getCompanyId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회사입니다."));

        jobPostingRepository.save(
                new JobPostingEntity(
                        registerInfo.getCompanyId(),
                        registerInfo.getJobPosition(),
                        registerInfo.getJobCompensation(),
                        registerInfo.getDescription(),
                        registerInfo.getSkill()
                )
        );
    }

    public List<JobPosting> search(Pageable pageable, String keyword) {
        return jobPostingRepository.findByKeyword(pageable, keyword);
    }

    public void edit(int jobPostingId, @Valid JobPostingEditInfo editInfo) {
        companyRepository.findById(editInfo.getCompanyId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회사입니다."));

        JobPostingEntity jobPostingEntity = jobPostingRepository.findById((long) jobPostingId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채용공고입니다."));

        jobPostingEntity.editJobPosting(editInfo);
        jobPostingRepository.save(jobPostingEntity);
    }

    public void remove(int companyId, int jobPostingId) {
        companyRepository.findById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회사입니다."));

        JobPostingEntity jobPostingEntity = jobPostingRepository.findById((long) jobPostingId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채용공고입니다."));

        if (jobPostingEntity.removeJobPosting(companyId, jobPostingId)) {
            jobPostingRepository.delete(jobPostingEntity);
        } else {
            throw new IllegalArgumentException("채용공고의 회사와 삭제 요청의 회사가 일치하지 않습니다.");
        }

    }

    public List<JobPosting> findDetail(int jobPostingId) {
        return jobPostingRepository.detailPosting(jobPostingId);
    }

    public List<Integer> findOtherJobPostings(int companyId, int jobPostingId) {
        List<Integer> otherJobPostings = jobPostingRepository.findOtherJobPostings(companyId, jobPostingId);
        return otherJobPostings.stream()
                .map(JobPostingEntity::getJobPostingId)
                .collect(Collectors.toList());
    }

    public void apply(int jobPostingId, JobPostingApplyRequest request) {
        JobPostingEntity jobPostingEntity = jobPostingRepository.findById((long) jobPostingId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채용공고입니다."));

        int userId = request.getUserId();

        if (jobPostingEntity.isApplicant(String.valueOf(userId))) {
            throw new IllegalArgumentException("이미 지원한 채용공고입니다.");
        } else {
            jobPostingEntity.addApplicant(userId);

            jobPostingRepository.save(jobPostingEntity);
        }
    }
}
