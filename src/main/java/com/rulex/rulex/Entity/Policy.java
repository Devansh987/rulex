package com.rulex.rulex.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(
        name = "policies",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"tenant_id", "policy_code"})
        }
)
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "policy_code", nullable = false)
    private String policyCode;

    @Column
    private String description;

    @NotNull
    @Column(nullable = false)
    private String status;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Policy() {
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPolicyCode() {
        return policyCode;
    }

    public void setPolicyCode(String policyCode) {
        this.policyCode = policyCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Policy{" +
                "id=" + id +
                ", policyCode='" + policyCode + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", tenant=" + tenant +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Policy policy = (Policy) o;
        return Objects.equals(id, policy.id) &&
                Objects.equals(policyCode, policy.policyCode) &&
                Objects.equals(description, policy.description) &&
                Objects.equals(status, policy.status) &&
                Objects.equals(tenant, policy.tenant) &&
                Objects.equals(createdAt, policy.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, policyCode, description, status, tenant, createdAt);
    }
}
