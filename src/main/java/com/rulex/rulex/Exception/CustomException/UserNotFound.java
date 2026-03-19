package com.rulex.rulex.Exception.CustomException;

public class UserNotFound extends RuntimeException{

    public UserNotFound(String message){
        super(message);
    }
}
