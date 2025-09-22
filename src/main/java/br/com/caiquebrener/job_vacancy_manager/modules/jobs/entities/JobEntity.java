package br.com.caiquebrener.job_vacancy_manager.modules.jobs.entities;

import br.com.caiquebrener.job_vacancy_manager.modules.company.entities.CompanyEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "job")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(
            description = "Unique identifier of the job",
            example = "550e8400-e29b-41d4-a716-446655440000",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private UUID id;
    @Schema(
            description = "Detailed description of the job position",
            example = "We are looking for a Senior Android Developer with experience in Kotlin"
    )
    private String description;

    @NotBlank(message = "This field is required")
    @Schema(
            description = "Job level",
            example = "Junior | Mid | Senior",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String level;
    @Schema(
            description = "List of benefits offered for this job",
            example = "Health insurance, GymPass, Home office allowance"
    )
    private String benefits;

    @ManyToOne()
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private CompanyEntity companyEntity;

    @Column(name = "company_id", nullable = false)
    private UUID company_id;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
