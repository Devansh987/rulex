package com.rulex.rulex.Exception.CustomException;

public class RuleNotFound extends RuntimeException{

    public  RuleNotFound(String message){
        super(message);
    }
}
