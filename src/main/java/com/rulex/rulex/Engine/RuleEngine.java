package com.rulex.rulex.Engine;

import com.rulex.rulex.DTO.DecisionClass;
import com.rulex.rulex.Entity.Rule;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class RuleEngine {

    public DecisionClass evaluate(List<Rule> rules, Map<String, Object> facts) {

        for (Rule rule : rules) {

            if (ruleMatches(rule, facts)) {
                return buildDecision(rule);
            }
        }

        return new DecisionClass(
                "NO_DECISION",
                "Condition not satisfied",
                null
        );
    }

    private boolean ruleMatches(Rule rule, Map<String, Object> facts) {

        String field = rule.getField();

        if (!facts.containsKey(field)) {
            return false;
        }

        Object factValue = facts.get(field);

        String operator = rule.getOperator();
        String ruleValue = rule.getValue();

        return compare(factValue, operator, ruleValue);
    }

    private boolean compare(Object factValue, String operator, String ruleValue) {

        // Numeric comparison
        if (factValue instanceof Number) {

            double fact = ((Number) factValue).doubleValue();
            double rule = Double.parseDouble(ruleValue);

            switch (operator) {

                case ">":
                    return fact > rule;

                case ">=":
                    return fact >= rule;

                case "<":
                    return fact < rule;

                case "<=":
                    return fact <= rule;

                case "==":
                    return fact == rule;

                case "!=":
                    return fact != rule;

                default:
                    return false;
            }
        }

        // String comparison
        if (factValue instanceof String) {

            String fact = (String) factValue;

            switch (operator) {

                case "==":
                    return fact.equals(ruleValue);

                case "!=":
                    return !fact.equals(ruleValue);

                default:
                    return false;
            }
        }

        return false;
    }

    private DecisionClass buildDecision(Rule rule) {

        String description =
                rule.getField()
                        + " "
                        + rule.getOperator()
                        + " "
                        + rule.getValue();

        return new DecisionClass(
                rule.getDecision(),
                "Matched Rule: " + description,
                description
        );
    }
}