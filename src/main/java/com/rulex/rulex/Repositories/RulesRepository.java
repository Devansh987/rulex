package com.rulex.rulex.Repositories;

import com.rulex.rulex.Entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RulesRepository extends JpaRepository<Rule,Long> {
    List<Rule> findByPolicyIdAndActiveTrueOrderByPriorityAsc(Long policyId);
}
