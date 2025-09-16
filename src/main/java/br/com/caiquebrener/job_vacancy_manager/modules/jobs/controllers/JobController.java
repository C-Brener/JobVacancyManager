package br.com.caiquebrener.job_vacancy_manager.modules.jobs.controllers;

import br.com.caiquebrener.job_vacancy_manager.modules.company.dto.CreateJobDTO;
import br.com.caiquebrener.job_vacancy_manager.modules.jobs.entities.JobEntity;
import br.com.caiquebrener.job_vacancy_manager.modules.jobs.usecase.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/job/")
public class JobController {
    @Autowired
    private CreateJobUseCase useCase;

    @PostMapping
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Object> create(
            @Valid @RequestBody CreateJobDTO dto,
            HttpServletRequest request
    ) {
        var companyId = request.getAttribute("company_id");

        var jobEntity = JobEntity.builder()
                .company_id(UUID.fromString(companyId.toString()))
                .benefits(dto.getBenefits())
                .description(dto.getDescription())
                .level(dto.getLevel())
                .build();

        JobEntity result = useCase.execute(jobEntity);
        return ResponseEntity.ok(result);
    }

}
