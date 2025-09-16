package br.com.caiquebrener.job_vacancy_manager.modules.candidate.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ProfileCandidateResponseDTO(
        String description,
        String username,
        String email,
        String name,
        UUID id
) {}
