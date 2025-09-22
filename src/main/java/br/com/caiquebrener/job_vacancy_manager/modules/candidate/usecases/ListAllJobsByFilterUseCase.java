package br.com.caiquebrener.job_vacancy_manager.modules.candidate.usecases;

import br.com.caiquebrener.job_vacancy_manager.modules.jobs.entities.JobEntity;
import br.com.caiquebrener.job_vacancy_manager.modules.jobs.repository.IJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllJobsByFilterUseCase {

    @Autowired
    IJobRepository repository;

    public List<JobEntity> execute(String filter) {
        return repository.findByDescriptionContainingIgnoreCase(filter);
    }
}
