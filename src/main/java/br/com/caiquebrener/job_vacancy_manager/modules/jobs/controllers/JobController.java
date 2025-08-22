package br.com.caiquebrener.job_vacancy_manager.modules.jobs.controllers;

import br.com.caiquebrener.job_vacancy_manager.modules.jobs.entities.JobEntity;
import br.com.caiquebrener.job_vacancy_manager.modules.jobs.usecase.CreateJobUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job/")
public class JobController {
    @Autowired
    private CreateJobUseCase useCase;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody JobEntity entity) {
        JobEntity result = useCase.execute(entity);
        return ResponseEntity.ok(result);
    }

}
