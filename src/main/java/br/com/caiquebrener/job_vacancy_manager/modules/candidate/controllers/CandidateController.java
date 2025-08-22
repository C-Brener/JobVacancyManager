package br.com.caiquebrener.job_vacancy_manager.modules.candidate.controllers;

import br.com.caiquebrener.job_vacancy_manager.modules.candidate.models.CandidateEntity;
import br.com.caiquebrener.job_vacancy_manager.modules.candidate.usecases.CreateCandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate/")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate) {
        var result = createCandidateUseCase.execute(candidate);
        return ResponseEntity.ok(result);
    }
}
