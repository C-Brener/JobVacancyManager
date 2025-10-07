package br.com.caiquebrener.job_vacancy_manager.exceptions.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("User Not Exists");
    }
}
