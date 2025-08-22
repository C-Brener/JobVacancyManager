package br.com.caiquebrener.job_vacancy_manager.modules.jobs.usecase;

import br.com.caiquebrener.job_vacancy_manager.modules.jobs.entities.JobEntity;
import br.com.caiquebrener.job_vacancy_manager.modules.jobs.repository.IJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private IJobRepository jobRepository;

    public JobEntity execute(JobEntity job) {

        return this.jobRepository.save(job);
    }

}
