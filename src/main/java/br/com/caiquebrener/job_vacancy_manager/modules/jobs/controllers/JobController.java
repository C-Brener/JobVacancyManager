package br.com.caiquebrener.job_vacancy_manager.modules.jobs.controllers;

import br.com.caiquebrener.job_vacancy_manager.modules.company.dto.CreateJobDTO;
import br.com.caiquebrener.job_vacancy_manager.modules.jobs.entities.JobEntity;
import br.com.caiquebrener.job_vacancy_manager.modules.jobs.usecase.CreateJobUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "Jobs",
        description = "Information about the jobs"
)
public class JobController {
    @Autowired
    private CreateJobUseCase useCase;

    @PostMapping
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(
            summary = "Register Jobs",
            description = "This function is responsible for register jobs inside the company"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)))
                    })
    })
    @SecurityRequirement(name = "jwt_auth")
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
