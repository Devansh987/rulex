package com.rulex.rulex.Repositories;

import com.rulex.rulex.Entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TenantRepository extends JpaRepository<Tenant,Long> {

    Tenant findByTenantCode(String TenantCode);
}
