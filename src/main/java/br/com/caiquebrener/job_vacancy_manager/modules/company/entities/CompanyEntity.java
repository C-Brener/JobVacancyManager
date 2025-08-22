package br.com.caiquebrener.job_vacancy_manager.modules.company.entities;

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
import java.util.Optional;
import java.util.UUID;

@Entity(name = "company")
@Data
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @Pattern(regexp = "^\\S+$", message = "The field must not contain a blank space")
    private String userName;

    @Email(message =  "The field must not contain a valid email address")
    private String email;

    @Length(min = 10, max = 100)
    private String password;
    private String url;
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;


}
