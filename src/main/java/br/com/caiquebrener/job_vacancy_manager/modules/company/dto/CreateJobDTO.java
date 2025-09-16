package br.com.caiquebrener.job_vacancy_manager.modules.company.dto;

import lombok.Data;

@Data
public class CreateJobDTO {

    private String description;
    private String level;
    private String benefits;
}
