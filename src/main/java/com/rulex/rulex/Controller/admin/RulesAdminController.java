package com.rulex.rulex.Controller.admin;


import com.rulex.rulex.Entity.Rule;
import com.rulex.rulex.Service.RulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/rules")
public class RulesAdminController {

    @Autowired
    private RulesService rulesService;

    @PostMapping("/tenant/{tenantCode}/policy/{policyCode}")
    public ResponseEntity<?> createRule(@RequestBody Rule rule, @PathVariable String tenantCode,@PathVariable String policyCode){
        Rule created_rule = rulesService.createRules(tenantCode,policyCode,rule);
        return new ResponseEntity<>(created_rule, HttpStatus.CREATED);
    }

    @GetMapping("/tenant/{tenantCode}/policy/{policyCode}")
    public ResponseEntity<?> getAllRules(@PathVariable String tenantCode,@PathVariable String policyCode){
        List<Rule> all_rules = rulesService.getRulesForPolicy(policyCode,tenantCode);
        return new ResponseEntity<>(all_rules,HttpStatus.FOUND);
    }

    @PutMapping("/{ruleId}")
    public ResponseEntity<?> updateRule(
            @PathVariable Long ruleId,
            @RequestBody Rule updatedRule) {

        Rule savedRule = rulesService.updateRule(ruleId, updatedRule);
        return new ResponseEntity<>(savedRule,HttpStatus.OK);
    }

    @DeleteMapping("/{ruleId}")
    public ResponseEntity<?> disableRule(@PathVariable Long ruleId){
        rulesService.disableRule(ruleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
