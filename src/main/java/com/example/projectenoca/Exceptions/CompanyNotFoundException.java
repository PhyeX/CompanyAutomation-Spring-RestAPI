package com.example.projectenoca.Exceptions;

public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException(int id) {
        super("Could not find company " + id);
    }
}