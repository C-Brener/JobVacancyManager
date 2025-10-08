package br.com.caiquebrener.job_vacancy_manager.modules.candidate.usecases;

import br.com.caiquebrener.job_vacancy_manager.exceptions.exceptions.JobNotFoundException;
import br.com.caiquebrener.job_vacancy_manager.exceptions.exceptions.UserNotFoundException;
import br.com.caiquebrener.job_vacancy_manager.modules.candidate.entities.ApplyJobEntity;
import br.com.caiquebrener.job_vacancy_manager.modules.candidate.entities.CandidateEntity;
import br.com.caiquebrener.job_vacancy_manager.modules.candidate.repository.IApplyJobRepository;
import br.com.caiquebrener.job_vacancy_manager.modules.candidate.repository.ICandidateRepository;
import br.com.caiquebrener.job_vacancy_manager.modules.jobs.entities.JobEntity;
import br.com.caiquebrener.job_vacancy_manager.modules.jobs.repository.IJobRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase useCase;

    // @Mock -> Used to mock the dependency of a specif class. In this example our repository is a dependency of UseCase
    @Mock
    private ICandidateRepository candidateRepository;

    @Mock
    private IJobRepository jobRepository;

    @Mock
    private IApplyJobRepository applyJobRepository;


    @Test
    public void GIVEN_USER_TRY_APPLY_JOB_WHEN_THE_USER_ID_NOT_EXISTS_THEN_GET_AN_EXCEPTION() {

        try {
            useCase.execute(null, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }

    }

    @Test
    public void GIVEN_USER_TRY_APPLY_JOB_WHEN_THE_JOB_ID_NOT_EXISTS_THEN_GET_AN_EXCEPTION() {
        UUID idCandidate = UUID.randomUUID();
        CandidateEntity candidate = new CandidateEntity();

        Mockito.when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));
        try {
            useCase.execute(idCandidate, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }

    }

    @Test
    public void GIVEN_USER_TRY_APPLY_JOB_WHEN_THE_EVERYTHING_WORKS_THEN_CREATE() {
        UUID idCandidate = UUID.randomUUID();
        UUID idJob = UUID.randomUUID();

        var applyJobEntity = ApplyJobEntity.builder().candidateId(idCandidate).jobId(idJob).build();

        var applyJobCreated = ApplyJobEntity.builder().id(UUID.randomUUID()).build();

        Mockito.when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntity()));
        Mockito.when(jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity()));

        Mockito.when(applyJobRepository.save(applyJobEntity)).thenReturn(applyJobCreated);

        var result = useCase.execute(idCandidate, idJob);

        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());
    }
}