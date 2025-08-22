package br.com.caiquebrener.job_vacancy_manager.modules.jobs.repository;

import br.com.caiquebrener.job_vacancy_manager.modules.jobs.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IJobRepository extends JpaRepository<JobEntity, UUID> {
}
