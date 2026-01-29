package com.rulex.rulex.Service;

import com.rulex.rulex.DTO.DecisionClass;
import com.rulex.rulex.Engine.RuleEngine;
import com.rulex.rulex.Entity.Policy;
import com.rulex.rulex.Entity.Rule;
import com.rulex.rulex.Entity.Tenant;
import com.rulex.rulex.Repositories.PolicyRepository;
import com.rulex.rulex.Repositories.RulesRepository;
import com.rulex.rulex.Repositories.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PolicyEvaluationService {

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private RulesRepository rulesRepository;

    @Autowired
    private RuleEngine ruleEngine;


    public DecisionClass evaluatePolicy(String tenantCode , String policyCode, Map<String,Object> facts){
        Tenant tenant = resolveTenant(tenantCode);
        Policy policy  = resolvePolicy(tenant.getId(),policyCode);
         List<Rule> rules = loadActiveRules(policy.getId());
         return ruleEngine.evaluate(rules,facts);
    }

    public Tenant resolveTenant(String tenantCode) {
        Tenant tenant = tenantRepository.findByTenantCode(tenantCode);
        if (tenant == null) {
            throw new RuntimeException("Tenant not found for tenantCode: " + tenantCode);
        }
        return tenant;
    }


    public Policy resolvePolicy(Long TenantId , String PolicyCode){
        Policy policy = policyRepository.findByTenantIdAndPolicyCode(TenantId,PolicyCode);
        if(policy==null){
            throw new RuntimeException("Policy not found with tenantCode"+TenantId+" and PolicyCode "+PolicyCode);
        }

        return  policy;
    }

    public List<Rule> loadActiveRules(Long policyId){
        List<Rule> rules = rulesRepository.findByPolicyIdAndActiveTrueOrderByPriorityAsc(policyId);
        return rules;
    }
}
