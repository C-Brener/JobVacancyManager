package br.com.caiquebrener.job_vacancy_manager.exceptions.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DataIntegrityViolationExceptionHandler {

    // This Handler can be resolved with a try catch too
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleUserAlreadyExists(DataIntegrityViolationException ex) {
        return ResponseEntity.badRequest().body("Invalid company_id. The company does not exist.");
    }
}