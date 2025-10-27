package br.com.caiquebrener.job_vacancy_manager.exceptions.exceptions;

public class CompanyNotFoundException extends RuntimeException{
    public CompanyNotFoundException(){
        super("Company Not Exists");
    }
}
