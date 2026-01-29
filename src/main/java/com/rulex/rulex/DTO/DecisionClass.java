package com.rulex.rulex.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@NoArgsConstructor
@AllArgsConstructor
public class DecisionClass {
    private String decision;
    private String Reason;
    private String matchedRules;
}
