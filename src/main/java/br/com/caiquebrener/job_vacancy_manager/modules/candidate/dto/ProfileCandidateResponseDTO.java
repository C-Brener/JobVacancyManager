package br.com.caiquebrener.job_vacancy_manager.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ProfileCandidateResponseDTO(
        @Schema(example = "Java Developer")
        String description,
        @Schema(example = "Brener")
        String username,
        @Schema(example = "brenercaique0806@gmail.com")
        String email,
        @Schema(example = "Android Developer")
        String name,
        UUID id
) {}
