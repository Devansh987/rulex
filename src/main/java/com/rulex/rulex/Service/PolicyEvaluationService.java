package com.rulex.rulex.Service;

import com.rulex.rulex.Repositories.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolicyEvaluationService {

    @Autowired
    private PolicyRepository policyRepository;

    
}
