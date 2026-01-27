package com.rulex.rulex.Service;
import com.rulex.rulex.Entity.Policy;
import com.rulex.rulex.Entity.Tenant;
import com.rulex.rulex.Repositories.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PolicyService {
        @Autowired
        private PolicyRepository policyRepository;

        @Autowired
        private TenantService tenantService;



        public Policy createPolicy(String tenantCode , Policy policy){
            if(policy == null){
                throw new IllegalArgumentException("policy is invalid");
            }
            Tenant tenant = tenantService.getTenantByCode(tenantCode);
            policy.setTenant(tenant);
            policy.setStatus("Active");
            policy.setCreatedAt(LocalDateTime.now());
            return policyRepository.save(policy);

        }

        public List<Policy> getALLPolicy(String tenantCode){
            Tenant tenant = tenantService.getTenantByCode(tenantCode);
           return policyRepository.findAllByTenantId(tenant.getId());
        }

     public Policy getPolicyByCode(String tenantCode,String policyCode){
           Tenant tenant = tenantService.getTenantByCode(tenantCode);
           Policy policy = policyRepository.findByTenantIdAndPolicyCode(tenant.getId(),policyCode);
           if(policy == null){
               throw new RuntimeException("policy not found");
           }
           return policy;
        }

    public Policy updatePolicy(Policy updatedPolicy,String policyCode,String tenantCode){
            if(updatedPolicy == null){
                throw new IllegalArgumentException("policy is invalid");
            }
            Policy existingPolicy = getPolicyByCode(tenantCode,policyCode);
            existingPolicy.setDescription(updatedPolicy.getDescription());
            existingPolicy.setStatus(updatedPolicy.getStatus());
            return policyRepository.save(existingPolicy);
        }

    public Policy disable(String policyCode,String tenantCode){
            Policy policy = getPolicyByCode(tenantCode,policyCode);
            policy.setStatus("Inactive");
            return policyRepository.save(policy);

        }
}
