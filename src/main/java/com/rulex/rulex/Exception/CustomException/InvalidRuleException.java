package com.rulex.rulex.Exception.CustomException;

public class InvalidRuleException extends RuntimeException{

    public InvalidRuleException(String message){
        super(message);
    }
}
