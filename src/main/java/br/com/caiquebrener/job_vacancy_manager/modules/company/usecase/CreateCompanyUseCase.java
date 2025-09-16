package br.com.caiquebrener.job_vacancy_manager.modules.company.usecase;

import br.com.caiquebrener.job_vacancy_manager.exceptions.exceptions.UserFoundException;
import br.com.caiquebrener.job_vacancy_manager.modules.company.entities.CompanyEntity;
import br.com.caiquebrener.job_vacancy_manager.modules.company.repository.ICompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private ICompanyRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity entity) {
        checkIfCompanyAlreadyExists(entity);
        encodePassword(entity);
        return this.repository.save(entity);
    }

    private void encodePassword(CompanyEntity entity) {
        var password = passwordEncoder.encode(entity.getPassword());
        entity.setPassword(password);
    }

    private void checkIfCompanyAlreadyExists(CompanyEntity entity) {
        repository
                .findByUserNameOrEmail(entity.getUserName(), entity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });
    }
}
