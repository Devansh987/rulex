package com.rulex.rulex.Controller.admin;


import com.rulex.rulex.Entity.Tenant;
import com.rulex.rulex.Service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/tenant")
public class TenantAdminController {

    @Autowired
    private TenantService tenantService;

    @PostMapping
    public ResponseEntity<Tenant> createTenant(@RequestBody Tenant tenant){
       Tenant saved =  tenantService.createTenant(tenant);
        return new ResponseEntity<>(saved,HttpStatus.CREATED);
    }

    @GetMapping("/{tenantCode}")
    public ResponseEntity<Tenant> getByTenantCode(@PathVariable String tenantCode){
        Tenant got = tenantService.getTenantByCode(tenantCode);
        return new ResponseEntity<>(got,HttpStatus.FOUND);
    }

    @PutMapping("/{tenantCode}")
    public ResponseEntity<Tenant> updateTenant(@RequestBody Tenant updatedTenant,@PathVariable String tenantCode){
        Tenant updated = tenantService.updateTenant(tenantCode,updatedTenant);
        return new ResponseEntity<>(updated,HttpStatus.OK);
    }


    @DeleteMapping("/{tenantCode}")
    public ResponseEntity<Void> disableTenant(@PathVariable String tenantCode) {
        tenantService.disableTenant(tenantCode);
        return ResponseEntity.noContent().build();
    }

}
