package com.rulex.rulex.DTO;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
public class EvaluationClass {
    private String tenantCode;
    private String policyCode;
    private Map<String, Object> facts;

}
