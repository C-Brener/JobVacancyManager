package br.com.caiquebrener.job_vacancy_manager.modules.company.repository;

import br.com.caiquebrener.job_vacancy_manager.modules.company.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ICompanyRepository extends JpaRepository<CompanyEntity, UUID> {
    Optional<CompanyEntity> findByUserNameOrEmail(String username, String email);
}
