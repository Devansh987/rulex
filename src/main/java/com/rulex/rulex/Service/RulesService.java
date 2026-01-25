package com.rulex.rulex.Service;

import com.rulex.rulex.Entity.Policy;
import com.rulex.rulex.Entity.Rules;
import com.rulex.rulex.Entity.Tenant;
import com.rulex.rulex.Repositories.RulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RulesService {

    @Autowired
    private RulesRepository  rulesRepository;

    @Autowired
    private TenantService tenantService;
    @Autowired
    private PolicyService policyService;

    protected void createRules(String tenantCode, String PolicyCode, Rules rule){
        Tenant tenant = tenantService.getTenantByCode(tenantCode);
        Policy policy = policyService.getPolicyByCode(tenantCode,PolicyCode);
        rule.setPolicy(policy);
        rule.setActive(true);
        rulesRepository.save(rule);
    }

    protected List<Rules> getRulesForPolicy(String policyCode,String tenantCode){
        Policy policy = policyService.getPolicyByCode(tenantCode,policyCode);
        return  rulesRepository.findByPolicyIdAndActiveTrueOrderByPriorityAsc(policy.getId());
    }

    protected void updateRule(Long ruleId,Rules updated){
        if(updated == null){
            throw new IllegalArgumentException("Invalid rules");
        }
        Rules existing = rulesRepository.findById(ruleId).orElseThrow(()-> new RuntimeException("Rule not found with id: " + ruleId));
        existing.setField(updated.getField());
        existing.setOperator(updated.getOperator());
        existing.setValue(updated.getValue());
        existing.setDecision(updated.getDecision());
        existing.setPriority(updated.getPriority());
        existing.setActive(updated.getActive());

        rulesRepository.save(existing);
    }

    protected void disableRule(Long ruleId){
        Rules existing = rulesRepository.findById(ruleId).orElseThrow(()-> new RuntimeException("Rule not found with id: " + ruleId));
        existing.setActive(false);
        rulesRepository.save(existing);
    }
}
