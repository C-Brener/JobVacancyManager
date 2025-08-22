package br.com.caiquebrener.job_vacancy_manager.modules.candidate.usecases;

import br.com.caiquebrener.job_vacancy_manager.exceptions.exceptions.UserFoundException;
import br.com.caiquebrener.job_vacancy_manager.modules.candidate.entities.CandidateEntity;
import br.com.caiquebrener.job_vacancy_manager.modules.candidate.repository.ICandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private ICandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidate) {
        candidateRepository
                .findByUserNameOrEmail(candidate.getUserName(), candidate.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });
        return this.candidateRepository.save(candidate);
    }
}
