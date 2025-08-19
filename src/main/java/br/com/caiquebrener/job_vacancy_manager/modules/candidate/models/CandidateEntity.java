package br.com.caiquebrener.job_vacancy_manager.modules.candidate.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Data
public class CandidateEntity {

    private UUID id = UUID.randomUUID();
    private String name;

    @Pattern(regexp = "^\\S+$", message = "The field must not contain a blank space")
    private String userName;

    @Email(message =  "The field must not contain a valid email address")
    private String email;

    @Length(min = 10, max = 100)
    private String password;
    private String description;
    private String curriculum;
}
