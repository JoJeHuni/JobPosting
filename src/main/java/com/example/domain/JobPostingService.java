package com.example.jehunonboarding.domain;

import com.example.jehunonboarding.repository.CompanyRepository;
import com.example.jehunonboarding.repository.JobPostingRepository;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

        jobPostingEntity.JobPostingEdit(editInfo);
        jobPostingRepository.save(jobPostingEntity);
    }

    public void remove(int companyId, int jobPostingId) {
        companyRepository.findById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회사입니다."));

        JobPostingEntity jobPostingEntity = jobPostingRepository.findById((long) jobPostingId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채용공고입니다."));

        if (jobPostingEntity.JobPostingRemove(companyId, jobPostingId)) {
            jobPostingRepository.delete(jobPostingEntity);
        } else {
            throw new IllegalArgumentException("채용공고의 회사와 삭제 요청의 회사가 일치하지 않습니다.");
        }

    }
}
