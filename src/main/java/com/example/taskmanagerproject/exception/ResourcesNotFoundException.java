package com.example.taskmanagerproject.exception;

public class ResourcesNotFoundException extends RuntimeException{
    public ResourcesNotFoundException(String message) {
        super(message);
    }
}
