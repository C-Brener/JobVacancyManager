package br.com.caiquebrener.job_vacancy_manager.modules.candidate.usecases;

import br.com.caiquebrener.job_vacancy_manager.exceptions.exceptions.UserFoundException;
import br.com.caiquebrener.job_vacancy_manager.modules.candidate.entities.CandidateEntity;
import br.com.caiquebrener.job_vacancy_manager.modules.candidate.repository.ICandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private ICandidateRepository candidateRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity entity) {
        checkIfUserAlreadyExists(entity);
        encodePassword(entity);
        return this.candidateRepository.save(entity);
    }

    private void encodePassword(CandidateEntity entity) {
        var password = passwordEncoder.encode(entity.getPassword());
        entity.setPassword(password);
    }

    private void checkIfUserAlreadyExists(CandidateEntity entity) {
        candidateRepository
                .findByUserNameOrEmail(entity.getUserName(), entity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });
    }
}
