package br.com.caiquebrener.job_vacancy_manager.modules.candidate.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(example = "Caique Brener", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(example = "brenerCaique", requiredMode = Schema.RequiredMode.REQUIRED)
    @Pattern(regexp = "^\\S+$", message = "The field must not contain a blank space")
    private String userName;

    @Email(message =  "The field must not contain a valid email address")
    @Schema(example = "brenercaique0806@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Length(min = 10, max = 100)
    @Schema(example = "examplePassword", minLength = 10, maxLength = 100, requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
