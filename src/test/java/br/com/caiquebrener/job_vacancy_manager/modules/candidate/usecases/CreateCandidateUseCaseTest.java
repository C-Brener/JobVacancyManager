package br.com.caiquebrener.job_vacancy_manager.modules.candidate.usecases;

import br.com.caiquebrener.job_vacancy_manager.exceptions.exceptions.UserFoundException;
import br.com.caiquebrener.job_vacancy_manager.modules.candidate.entities.CandidateEntity;
import br.com.caiquebrener.job_vacancy_manager.modules.candidate.repository.ICandidateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CreateCandidateUseCaseTest {

    @InjectMocks
    private CreateCandidateUseCase useCase;

    @Mock
    private ICandidateRepository candidateRepository;

    @Mock
    private PasswordEncoder passwordEncoder;


    @Test
    public void GIVEN_USER_TRY_APPLY_CREATE_A_PROFILE_WHEN_THE_USER_ALREADY_EXISTS_THEN_GET_AN_EXCEPTION() {
        CandidateEntity entity = CandidateEntity
                .builder()
                .userName("jhon doe")
                .password("1234567890")
                .email("jhonDoe@mockemail.com")
                .build();
        Mockito.when(candidateRepository.findByUserNameOrEmail(
                        entity.getUserName(),
                        entity.getEmail()))
                .thenReturn(Optional.of(entity));

        try {
            useCase.execute(entity);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserFoundException.class);
        }

    }

    @Test
    public void GIVEN_USER_TRY_APPLY_CREATE_A_PROFILE_WHEN_THE_USER_NOT_EXISTS_THEN_WORKS_AS_EXPECTED() {
        CandidateEntity entity = CandidateEntity
                .builder()
                .userName("jhon doe")
                .password("1234567890")
                .email("jhonDoe@mockemail.com")
                .build();

        Mockito.when(candidateRepository.findByUserNameOrEmail(
                        entity.getUserName(),
                        entity.getEmail()))
                .thenReturn(Optional.empty());

        Mockito.when(passwordEncoder.encode(entity.getPassword())).thenReturn("LASJKLAJSAjs211w0192");

        Mockito.when(candidateRepository.save(entity)).thenReturn(entity);

        var result = useCase.execute(entity);

        assertThat(Objects.equals(result.getUserName(), entity.getUserName()));
    }
}