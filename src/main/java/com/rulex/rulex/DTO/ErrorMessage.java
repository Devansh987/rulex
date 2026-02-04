package com.rulex.rulex.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ErrorMessage {
    String error;
    String message;
    String status;
}
