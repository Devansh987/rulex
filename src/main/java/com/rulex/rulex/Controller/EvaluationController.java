package com.rulex.rulex.Controller;

import com.rulex.rulex.DTO.DecisionClass;
import com.rulex.rulex.DTO.EvaluationClass;
import com.rulex.rulex.Service.PolicyEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client/api")
public class EvaluationController {
    @Autowired
    private PolicyEvaluationService policyEvaluationService;

    @PostMapping("/evaluate")
    public ResponseEntity<DecisionClass> evaluateImpl(@RequestBody EvaluationClass evaluationClass){
        DecisionClass decisionClass = policyEvaluationService.evaluatePolicy(evaluationClass.getTenantCode(),
                evaluationClass.getPolicyCode(),evaluationClass.getFacts());
        return new ResponseEntity<>(decisionClass, HttpStatus.OK);
    }
}
