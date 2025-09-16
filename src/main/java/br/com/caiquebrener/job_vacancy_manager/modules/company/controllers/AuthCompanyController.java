package br.com.caiquebrener.job_vacancy_manager.modules.company.controllers;

import br.com.caiquebrener.job_vacancy_manager.modules.company.dto.AuthCompanyDTO;
import br.com.caiquebrener.job_vacancy_manager.modules.company.usecase.AuthCompanyUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/company/")
public class AuthCompanyController {

    @Autowired
    AuthCompanyUseCase useCase;

    @PostMapping("auth")
    public ResponseEntity<Object> create(@RequestBody AuthCompanyDTO dto) {
        try {
            return ResponseEntity.ok().body(useCase.execute(dto));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
