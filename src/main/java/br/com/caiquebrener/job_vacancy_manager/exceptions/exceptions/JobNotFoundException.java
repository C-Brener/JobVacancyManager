package br.com.caiquebrener.job_vacancy_manager.exceptions.exceptions;

public class JobNotFoundException extends RuntimeException{
    public JobNotFoundException(){
        super("Job Not Exists");
    }
}
