package br.com.caiquebrener.job_vacancy_manager.modules.candidate.usecases;

import br.com.caiquebrener.job_vacancy_manager.exceptions.exceptions.UserNotFoundException;
import br.com.caiquebrener.job_vacancy_manager.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.caiquebrener.job_vacancy_manager.modules.candidate.entities.CandidateEntity;
import br.com.caiquebrener.job_vacancy_manager.modules.candidate.repository.ICandidateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class ProfileCandidateUseCaseTest {

    @InjectMocks
    private ProfileCandidateUseCase useCase;

    @Mock
    private ICandidateRepository repository;

    @Test
    public void GIVEN_USER_TRY_GET_YOUR_PROFILE_WHEN_THE_USER_ID_NOT_EXISTS_THEN_GET_AN_EXCEPTION() {
        UUID id = UUID.randomUUID();

        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> useCase.execute(id));
    }

    @Test
    public void GIVEN_USER_TRY_GET_YOUR_PROFILE_WHEN_THE_USER_ID_EXISTS_THEN_GET_A_DTO() {
        UUID id = UUID.randomUUID();
        CandidateEntity entity = CandidateEntity
                .builder()
                .id(id)
                .name("Jhon Doe")
                .description("developer")
                .userName("jhon doe")
                .password("1234567890")
                .email("jhonDoe@mockemail.com")
                .build();

        ProfileCandidateResponseDTO dto = ProfileCandidateResponseDTO.builder()
                .id(id)
                .name("Jhon Doe")
                .description("developer")
                .username("jhon doe")
                .email("jhonDoe@mockemail.com")
                .build();

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(entity));

        assertEquals(useCase.execute(id), dto);
    }

}