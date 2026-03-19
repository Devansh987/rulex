package com.rulex.rulex.Service;

import com.rulex.rulex.Entity.Policy;
import com.rulex.rulex.Entity.Rule;
import com.rulex.rulex.Entity.Tenant;
import com.rulex.rulex.Exception.CustomException.RuleNotFound;
import com.rulex.rulex.Repositories.RulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RulesService {

    @Autowired
    private RulesRepository  rulesRepository;

    @Autowired
    private TenantService tenantService;
    @Autowired
    private PolicyService policyService;

    public  Rule createRules(String tenantCode, String PolicyCode, Rule rule){
        Tenant tenant = tenantService.getTenantByCode(tenantCode);
        Policy policy = policyService.getPolicyByCode(tenantCode,PolicyCode);
        rule.setPolicy(policy);
        rule.setActive(true);
        rule.setCreated_at(LocalDateTime.now());
        return rulesRepository.save(rule);
    }

    public  List<Rule> getRulesForPolicy(String policyCode, String tenantCode){
        Policy policy = policyService.getPolicyByCode(tenantCode,policyCode);
        return  rulesRepository.findByPolicyIdAndActiveTrueOrderByPriorityAsc(policy.getId());
    }

    public  Rule updateRule(Long ruleId, Rule updated){
        if(updated == null){
            throw new IllegalArgumentException("Invalid rules");
        }
        Rule existing = rulesRepository.findById(ruleId).orElseThrow(()-> new RuntimeException("Rule not found with id: " + ruleId));
        existing.setField(updated.getField());
        existing.setOperator(updated.getOperator());
        existing.setValue(updated.getValue());
        existing.setDecision(updated.getDecision());
        existing.setPriority(updated.getPriority());
        existing.setActive(updated.getActive());
        return rulesRepository.save(existing);
    }

    public void disableRule(Long ruleId){
        Rule existing = rulesRepository.findById(ruleId).orElseThrow(()-> new RuleNotFound("Rule not found with id: " + ruleId));
        existing.setActive(false);
        rulesRepository.save(existing);
    }
}
