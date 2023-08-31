package com.example.exception;

public class ResourceAlreadyExistsException extends RuntimeException{
    public ResourceAlreadyExistsException(String entity){
        super(entity);
    }
}
