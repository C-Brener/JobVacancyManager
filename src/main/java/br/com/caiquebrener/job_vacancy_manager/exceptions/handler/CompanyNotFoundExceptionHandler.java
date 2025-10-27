package br.com.caiquebrener.job_vacancy_manager.exceptions.handler;

import br.com.caiquebrener.job_vacancy_manager.exceptions.exceptions.CompanyNotFoundException;
import br.com.caiquebrener.job_vacancy_manager.exceptions.exceptions.JobNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CompanyNotFoundExceptionHandler {

    // This Handler can be resolved with a try catch too
    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<String> handleUserAlreadyExists(CompanyNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}