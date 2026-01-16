package com.rulex.rulex.Entity;


//import com.rulex.rulex.Entity.Tenant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "policies",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"tenant_id", "policy_code"})
        }
)
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

}
