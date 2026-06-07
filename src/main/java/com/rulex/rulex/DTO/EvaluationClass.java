package com.rulex.rulex.DTO;

import java.util.Map;
import java.util.Objects;

public class EvaluationClass {
    private String tenantCode;
    private String policyCode;
    private Map<String, Object> facts;

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public String getPolicyCode() {
        return policyCode;
    }

    public void setPolicyCode(String policyCode) {
        this.policyCode = policyCode;
    }

    public Map<String, Object> getFacts() {
        return facts;
    }

    public void setFacts(Map<String, Object> facts) {
        this.facts = facts;
    }

    @Override
    public String toString() {
        return "EvaluationClass{" +
                "tenantCode='" + tenantCode + '\'' +
                ", policyCode='" + policyCode + '\'' +
                ", facts=" + facts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EvaluationClass that = (EvaluationClass) o;
        return Objects.equals(tenantCode, that.tenantCode) &&
                Objects.equals(policyCode, that.policyCode) &&
                Objects.equals(facts, that.facts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenantCode, policyCode, facts);
    }
}
