package com.rulex.rulex.Repositories;

import com.rulex.rulex.Entity.Rules;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RulesRepository extends JpaRepository<Rules,Long> {
    List<Rules> findByPolicyIdAndActiveTrueOrderByPriorityAsc(Long policyId);
}
