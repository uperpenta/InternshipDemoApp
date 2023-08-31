package com.example.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String member, long id){
        super(member);
    }
    
}
