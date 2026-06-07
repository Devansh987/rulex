package com.rulex.rulex.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Tenant")
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "tenant_code", nullable = false, unique = true)
    private String tenantCode;

    @NotBlank
    @Column(name = "tenant_name", nullable = false)
    private String tenantName;

    @NotNull
    @Column(nullable = false)
    private String status;

    @NotNull
    @Column(name = "Created_At", nullable = false)
    private LocalDateTime createdAt;

    public Tenant() {
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "id=" + id +
                ", tenantCode='" + tenantCode + '\'' +
                ", tenantName='" + tenantName + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tenant tenant = (Tenant) o;
        return Objects.equals(id, tenant.id) &&
                Objects.equals(tenantCode, tenant.tenantCode) &&
                Objects.equals(tenantName, tenant.tenantName) &&
                Objects.equals(status, tenant.status) &&
                Objects.equals(createdAt, tenant.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tenantCode, tenantName, status, createdAt);
    }
}
