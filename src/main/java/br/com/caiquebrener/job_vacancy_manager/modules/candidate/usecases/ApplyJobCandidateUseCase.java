package br.com.caiquebrener.job_vacancy_manager.modules.candidate.usecases;

import br.com.caiquebrener.job_vacancy_manager.exceptions.exceptions.JobNotFoundException;
import br.com.caiquebrener.job_vacancy_manager.exceptions.exceptions.UserNotFoundException;
import br.com.caiquebrener.job_vacancy_manager.modules.candidate.entities.ApplyJobEntity;
import br.com.caiquebrener.job_vacancy_manager.modules.candidate.repository.IApplyJobRepository;
import br.com.caiquebrener.job_vacancy_manager.modules.candidate.repository.ICandidateRepository;
import br.com.caiquebrener.job_vacancy_manager.modules.jobs.repository.IJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private ICandidateRepository candidateRepository;

    @Autowired
    private IJobRepository jobRepository;

    @Autowired
    private IApplyJobRepository applyJobRepository;

    // Candidate ID
    // Vacancy ID
    public ApplyJobEntity execute(UUID candidateId, UUID jobID) {
        // check if user exists
        this.candidateRepository.findById(candidateId).orElseThrow(UserNotFoundException::new);

        // check if vacancy exists
        this.jobRepository.findById(jobID).orElseThrow(JobNotFoundException::new);

        // Create Candidate Subscription

        var applyJob = ApplyJobEntity.builder().candidateId(candidateId).jobId(jobID).build();

        return applyJobRepository.save(applyJob);
    }
}
