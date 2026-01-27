package com.rulex.rulex.Controller.admin;


import com.rulex.rulex.Entity.Policy;
import com.rulex.rulex.Service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/policy")
public class PolicyAdminController {

    @Autowired
    private PolicyService policyService;

    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody Policy policy,@PathVariable String tenantCode){
        Policy latest = policyService.createPolicy(tenantCode,policy);
        return new ResponseEntity<>(latest, HttpStatus.CREATED);
    }

    @PutMapping("/tenant/{tenantCode}/{policyCode}")
    public ResponseEntity<?> updatePolicy(@RequestBody Policy policy,@PathVariable String policyCode,@PathVariable String tenantCode){
        Policy updated = policyService.updatePolicy(policy, policyCode,tenantCode);
        return new ResponseEntity<>(updated,HttpStatus.OK);
    }


    @GetMapping("/tenant/{tenantCode}/{policyCode}")
    public ResponseEntity<?> getPolicyById(@PathVariable String policyCode,@PathVariable String tenantCode){
        Policy find = policyService.getPolicyByCode(tenantCode,policyCode);
        return new ResponseEntity<>(find,HttpStatus.FOUND);

    }

    @GetMapping("/{tenantCode}")
    public ResponseEntity<?>  getAllPolicy(@PathVariable String tenantCode){
        List<Policy> policies = policyService.getALLPolicy(tenantCode);
        return new ResponseEntity<>(policies,HttpStatus.FOUND);

    }

    @DeleteMapping("/tenant/{tenantCode}/{policyCode}")
    public ResponseEntity<?> disablePolicy(@PathVariable String policyCode ,@PathVariable String tenantCode){
        policyService.disable(policyCode,tenantCode);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
