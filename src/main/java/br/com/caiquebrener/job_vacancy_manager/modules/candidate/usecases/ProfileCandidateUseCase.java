package br.com.caiquebrener.job_vacancy_manager.modules.candidate.usecases;

import br.com.caiquebrener.job_vacancy_manager.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.caiquebrener.job_vacancy_manager.modules.candidate.repository.ICandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private ICandidateRepository repository;

    public ProfileCandidateResponseDTO execute(UUID idCandidate) {
        var candidate = repository.findById(idCandidate).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found");
        });
        return ProfileCandidateResponseDTO
                .builder()
                .description(candidate.getDescription())
                .email(candidate.getEmail())
                .username(candidate.getUserName())
                .id(candidate.getId())
                .name(candidate.getName()).build();
    }
}
