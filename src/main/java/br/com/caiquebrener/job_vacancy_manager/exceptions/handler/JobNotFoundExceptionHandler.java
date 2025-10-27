package br.com.caiquebrener.job_vacancy_manager.exceptions.handler;

import br.com.caiquebrener.job_vacancy_manager.exceptions.exceptions.JobNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class JobNotFoundExceptionHandler {

    // This Handler can be resolved with a try catch too
    @ExceptionHandler(JobNotFoundException.class)
    public ResponseEntity<String> handleUserAlreadyExists(JobNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}