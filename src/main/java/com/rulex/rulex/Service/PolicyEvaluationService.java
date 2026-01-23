package com.rulex.rulex.Service;

import com.rulex.rulex.Entity.Policy;
import com.rulex.rulex.Entity.Rules;
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


    private List<Rules> evaluatePolicy(String tenantCode , String policyCode, Map<Policy,Object> inputRules){
        Tenant tenant = resolveTenant(tenantCode);
        Policy policy  = resolvePolicy(tenant.getId(),policyCode);
        return loadActiveRules(policy.getId());
    }



    private Tenant resolveTenant(String tenantCode) {
        Tenant tenant = tenantRepository.findByTenantCode(tenantCode);
        if (tenant == null) {
            throw new RuntimeException("Tenant not found for tenantCode: " + tenantCode);
        }
        return tenant;
    }


    private Policy resolvePolicy(Long TenantId , String PolicyCode){
        Policy policy = policyRepository.findByTenantIdAndPolicyCode(TenantId,PolicyCode);
        if(policy==null){
            throw new RuntimeException("Policy not found with tenantCode"+TenantId+" and PolicyCode "+PolicyCode);
        }

        return  policy;
    }

    private List<Rules> loadActiveRules(Long policyId){
        List<Rules> rules = rulesRepository.findByPolicyIdAndActiveTrueOrderByPriorityAsc(policyId);
        return rules;
    }
}
