package br.com.caiquebrener.job_vacancy_manager.exceptions.handler;

import br.com.caiquebrener.job_vacancy_manager.exceptions.exceptions.UserFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserFoundExceptionHandler {

    // This Handler can be resolved with a try catch too
    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<String> handleUserAlreadyExists(UserFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}