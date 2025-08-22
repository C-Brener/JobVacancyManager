package br.com.caiquebrener.job_vacancy_manager.modules.candidate.repository;

import br.com.caiquebrener.job_vacancy_manager.modules.candidate.models.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ICandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    Optional<CandidateEntity> findByUserNameOrEmail(String userName, String email);
}
