package com.rulex.rulex.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Rules")
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String field;

    @NotBlank
    @Column(nullable = false)
    private String operator;

    @NotBlank
    @Column(nullable = false)
    private String decision;

    @NotBlank
    @Column(nullable = false)
    private String value;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private Integer priority;

    @NotNull
    @Column(nullable = false)
    private Boolean active;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private Policy policy;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Rule() {
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "id=" + id +
                ", field='" + field + '\'' +
                ", operator='" + operator + '\'' +
                ", decision='" + decision + '\'' +
                ", value='" + value + '\'' +
                ", priority=" + priority +
                ", active=" + active +
                ", policy=" + policy +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return Objects.equals(id, rule.id) &&
                Objects.equals(field, rule.field) &&
                Objects.equals(operator, rule.operator) &&
                Objects.equals(decision, rule.decision) &&
                Objects.equals(value, rule.value) &&
                Objects.equals(priority, rule.priority) &&
                Objects.equals(active, rule.active) &&
                Objects.equals(policy, rule.policy) &&
                Objects.equals(createdAt, rule.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, field, operator, decision, value, priority, active, policy, createdAt);
    }
}
