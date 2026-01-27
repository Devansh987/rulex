package com.rulex.rulex.Repositories;

import com.rulex.rulex.Entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PolicyRepository extends JpaRepository<Policy,Long> {

    Policy findByTenantIdAndPolicyCode(Long tenantCode ,String PolicyCode);

    List<Policy> findAllByTenantId(Long tenantID);
}
