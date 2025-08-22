package br.com.caiquebrener.job_vacancy_manager.exceptions.exceptions;

public class UserFoundException extends RuntimeException{
    public  UserFoundException(){
        super("User Already Exists");
    }
}
