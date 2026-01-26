package com.rulex.rulex.Controller.admin;


import com.rulex.rulex.Entity.Tenant;
import com.rulex.rulex.Service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/tenant")
@ControllerAdvice
public class TenantAdminController {

    @Autowired
    private TenantService tenantService;

    @PostMapping("/createTenant")
    public ResponseEntity<Tenant> createTenant(@RequestBody Tenant tenant){
       Tenant saved =  tenantService.createTenant(tenant);
        return new ResponseEntity<>(saved,HttpStatus.CREATED);
    }
}
