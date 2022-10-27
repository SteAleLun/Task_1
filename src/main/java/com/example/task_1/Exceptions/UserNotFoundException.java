package com.example.task_1.Exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException{
    UserNotFoundException(UUID id){
        super("Could not find user" + id);
    }
}
