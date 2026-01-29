package com.rulex.rulex.Engine;

import com.rulex.rulex.DTO.DecisionClass;
import com.rulex.rulex.Entity.Rule;
import jakarta.validation.constraints.Null;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class RuleEngine {


    public  DecisionClass evaluate(List<Rule> rules, Map<String,Object> fact){

        for(Rule rule: rules){
            if(ruleMatches(rule,fact)){
                return buildDecision(rule);
            }
        }

        return new DecisionClass(
                "No Decision",
                "Condition Not Satisfied",
                null
        );
    }



    private boolean ruleMatches(Rule rule, Map<String,Object> facts){
        String field = rule.getField();
        if(!facts.containsKey(field)) return false;

        Object factValue = facts.get(field);
        String operator = rule.getOperator();
        String ruleValue = rule.getValue();

        return compare(factValue,operator,ruleValue);
    }

    private boolean compare(Object factValue,String operator,String ruleValue){

        if(factValue instanceof Number){
            double fact = ((Number) factValue).doubleValue();
            double rule = Double.parseDouble(ruleValue);

            return switch(operator){
                case ">"  -> fact > rule;
                case ">=" -> fact >= rule;
                case "<"  -> fact < rule;
                case "<=" -> fact <= rule;
                case "==" -> fact == rule;
                case "!=" -> fact != rule;
                default   -> false;
            };
        }

        if (factValue instanceof String fact) {

            return switch (operator) {
                case "==" -> fact.equals(ruleValue);
                case "!=" -> !fact.equals(ruleValue);
                default   -> false;
            };
        }

        return false;
    }

    private DecisionClass buildDecision(Rule rule){
        String description = rule.getField() + " " + rule.getOperator() + " " + rule.getValue();

        return new DecisionClass( rule.getDecision(), "Matched Rule" + description,description);

    }
}
