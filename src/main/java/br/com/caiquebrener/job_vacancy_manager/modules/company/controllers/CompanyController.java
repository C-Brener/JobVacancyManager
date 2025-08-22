package br.com.caiquebrener.job_vacancy_manager.modules.company.controllers;

import br.com.caiquebrener.job_vacancy_manager.modules.company.entities.CompanyEntity;
import br.com.caiquebrener.job_vacancy_manager.modules.company.usecase.CreateCompanyUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company/")
public class CompanyController {

    @Autowired
    private CreateCompanyUseCase useCase;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity company) {
        var result = useCase.execute(company);
        return ResponseEntity.ok(result);
    }
}
