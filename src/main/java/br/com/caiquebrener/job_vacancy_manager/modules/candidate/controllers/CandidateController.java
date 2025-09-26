package br.com.caiquebrener.job_vacancy_manager.modules.candidate.controllers;

import br.com.caiquebrener.job_vacancy_manager.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.caiquebrener.job_vacancy_manager.modules.candidate.entities.CandidateEntity;
import br.com.caiquebrener.job_vacancy_manager.modules.candidate.usecases.CreateCandidateUseCase;
import br.com.caiquebrener.job_vacancy_manager.modules.candidate.usecases.ListAllJobsByFilterUseCase;
import br.com.caiquebrener.job_vacancy_manager.modules.candidate.usecases.ProfileCandidateUseCase;
import br.com.caiquebrener.job_vacancy_manager.modules.jobs.entities.JobEntity;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate/")
@Tag(
        name = "Candidate",
        description = "Information about the candidate"
)
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase jobsByFilterUseCase;

    @PostMapping
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(array = @ArraySchema(schema = @Schema(implementation = CandidateEntity.class)))
                    })
            ,
            @ApiResponse(
                    responseCode = "400",
                    description = "User already exists")
    })
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate) {
        var result = createCandidateUseCase.execute(candidate);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(array = @ArraySchema(schema = @Schema(implementation = ProfileCandidateResponseDTO.class)))
                    })
            ,
            @ApiResponse(
                    responseCode = "400",
                    description = "User not found")
    })
    @Operation(
            summary = "Candidate profile",
            description = "This function is responsible for researching all information about candidate's profile"
    )
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> get(HttpServletRequest request) {
        var idCandidate = request.getAttribute("candidate_id");
        try {
            var result = profileCandidateUseCase.execute(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("job")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(
            summary = "List of available positions for the candidate",
            description = "This function is responsible for listing all available vacancies based on the filter."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)))
                    })
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<List<JobEntity>> findJobByFilter(@RequestParam String filter) {
        return ResponseEntity.ok(jobsByFilterUseCase.execute(filter));
    }
}
