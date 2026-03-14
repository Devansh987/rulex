package com.rulex.rulex.Exception.GlobalExceptionHandller;

import com.rulex.rulex.Exception.CustomException.RuleNotFound;
import com.rulex.rulex.Exception.CustomException.TenantNotFOUNDEXCEPTION;
import com.rulex.rulex.Exception.CustomException.UserNotFound;
import com.rulex.rulex.Exception.ErrorResponse;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandeller {

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<?> handleUserNotFound(UserNotFound ur){
        ErrorResponse er = new ErrorResponse(ur.getMessage(),404);
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TenantNotFOUNDEXCEPTION.class)
    public  ResponseEntity<?> handleTenantNotFound(TenantNotFOUNDEXCEPTION ur){
        ErrorResponse er = new ErrorResponse(ur.getMessage(),404);
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuleNotFound.class)
    public ResponseEntity<?> HandleRuleNotFound(RuleNotFound ur){
        ErrorResponse er = new ErrorResponse(ur.getMessage(),404);
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }
}
