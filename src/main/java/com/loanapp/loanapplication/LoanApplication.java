package com.loanapp.loanapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.loanapp.loanapplication","com.loanapp.loan","com.loanapp.cache","com.loanapp.exceptions","com.loanapp.loan","com.loanapp.restcontroller","com.loanapp.services","com.loanapp.servicesimpl","com.loanapp.verification"})
public class LoanApplication 
{
    public static void main( String[] args )
    {
       SpringApplication.run(LoanApplication.class, args);     
    }
}
