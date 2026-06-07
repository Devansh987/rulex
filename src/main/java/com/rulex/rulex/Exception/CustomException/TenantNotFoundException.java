package com.rulex.rulex.Exception.CustomException;

public class TenantNotFoundException extends RuntimeException {
    public TenantNotFoundException(String message){
        super(message);
    }
}
