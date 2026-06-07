package com.rulex.rulex.Exception.GlobalExceptionHandler;

import com.rulex.rulex.Exception.CustomException.RuleNotFound;
import com.rulex.rulex.Exception.CustomException.TenantNotFoundException;
import com.rulex.rulex.Exception.CustomException.UserNotFound;
import com.rulex.rulex.Exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<?> handleUserNotFound(UserNotFound ur){
        ErrorResponse er = new ErrorResponse(ur.getMessage(),404);
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TenantNotFoundException.class)
    public  ResponseEntity<?> handleTenantNotFound(TenantNotFoundException ur){
        ErrorResponse er = new ErrorResponse(ur.getMessage(),404);
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuleNotFound.class)
    public ResponseEntity<?> handleRuleNotFound(RuleNotFound ur){
        ErrorResponse er = new ErrorResponse(ur.getMessage(),404);
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }
}
