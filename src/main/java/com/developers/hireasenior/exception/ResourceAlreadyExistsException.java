package com.developers.hireasenior.exception;

public class ResourceAlreadyExistsException extends RuntimeException{
    public ResourceAlreadyExistsException(String resource) {
        super("Resource already exists: " +  resource);
    }
}
