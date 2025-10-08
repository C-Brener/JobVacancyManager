package br.com.caiquebrener.job_vacancy_manager.modules.candidate.repository;

import br.com.caiquebrener.job_vacancy_manager.modules.candidate.entities.ApplyJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {}
