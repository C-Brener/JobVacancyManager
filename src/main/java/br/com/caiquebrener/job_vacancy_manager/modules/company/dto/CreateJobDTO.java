package br.com.caiquebrener.job_vacancy_manager.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobDTO {
    @Schema(example = "We are looking for a skilled Android Developer to join our mobile team.", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
    @Schema(example = "Mid Level", requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;
    @Schema(example = "Health insurance, meal voucher, home office support", requiredMode = Schema.RequiredMode.REQUIRED)
    private String benefits;
}
