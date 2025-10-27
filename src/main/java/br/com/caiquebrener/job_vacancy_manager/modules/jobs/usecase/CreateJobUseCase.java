package br.com.caiquebrener.job_vacancy_manager.modules.jobs.usecase;

import br.com.caiquebrener.job_vacancy_manager.exceptions.exceptions.CompanyNotFoundException;
import br.com.caiquebrener.job_vacancy_manager.modules.company.repository.ICompanyRepository;
import br.com.caiquebrener.job_vacancy_manager.modules.jobs.entities.JobEntity;
import br.com.caiquebrener.job_vacancy_manager.modules.jobs.repository.IJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private IJobRepository jobRepository;

    @Autowired
    ICompanyRepository companyRepository;

    public JobEntity execute(JobEntity job) {
        companyRepository.findById(job.getCompany_id()).orElseThrow(CompanyNotFoundException::new);

        return this.jobRepository.save(job);
    }

}
