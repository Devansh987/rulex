package com.rulex.rulex.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    String Message;
    int code;
}
