package com.rulex.rulex.Service;


import com.rulex.rulex.Entity.Tenant;
import com.rulex.rulex.Repositories.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    private void createTenant(Tenant tenant) {
        if (tenant == null) {
            throw new IllegalArgumentException("Tenant cannot be null");
        }
        tenantRepository.save(tenant);
    }

    private Tenant getTenantByCode(String tenantCode) {
        Tenant tenant = tenantRepository.findByTenantCode(tenantCode);
        if (tenant == null) {
            throw new RuntimeException("Tenant not found for tenantCode: " + tenantCode);
        }
        return tenant;
    }

   private void updateTenant(String code, Tenant updated) {
        if (updated == null) {
            throw new IllegalArgumentException("Updated tenant data cannot be null");
        }
        Tenant existing = getTenantByCode(code); // reuse validation
        existing.setTenant_name(updated.getTenant_name());
        existing.setStatus(updated.getStatus());
        tenantRepository.save(existing);
    }
    private void disableTenant(String tenantCode){
        if(tenantCode==null){
            throw new IllegalArgumentException("TenantCode is Empty");
        }
        Tenant tenant = getTenantByCode(tenantCode);
        tenant.setStatus("Inactive");
        tenantRepository.save(tenant);
    }


}
