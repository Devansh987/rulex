package com.rulex.rulex.Service;
import com.rulex.rulex.Entity.Policy;
import com.rulex.rulex.Entity.Tenant;
import com.rulex.rulex.Repositories.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolicyService {
        @Autowired
        private PolicyRepository policyRepository;

        @Autowired
        private TenantService tenantService;



        protected void createPolicy(String tenantCode , Policy policy){
            if(policy == null){
                throw new IllegalArgumentException("policy is invalid");
            }
            Tenant tenant = tenantService.getTenantByCode(tenantCode);
            policy.setTenant(tenant);
            policy.setStatus("Active");
            policyRepository.save(policy);

        }

        protected Policy getPolicyByCode(String tenantCode,String policyCode){
           Tenant tenant = tenantService.getTenantByCode(tenantCode);
           Policy policy = policyRepository.findByTenantIdAndPolicyCode(tenant.getId(),policyCode);
           if(policy == null){
               throw new RuntimeException("policy not found");
           }
           return policy;
        }

        protected void updatePolicy(Policy updatedPolicy,String policyCode,String tenantCode){
            if(updatedPolicy == null){
                throw new IllegalArgumentException("policy is invalid");
            }
            Policy existingPolicy = getPolicyByCode(tenantCode,policyCode);
            existingPolicy.setDescription(updatedPolicy.getDescription());
            existingPolicy.setStatus(updatedPolicy.getStatus());
            policyRepository.save(existingPolicy);
        }

        protected void disable(String policyCode,String tenantCode){
            Policy policy = getPolicyByCode(tenantCode,policyCode);
            policy.setStatus("Inactive");
            policyRepository.save(policy);

        }
}
