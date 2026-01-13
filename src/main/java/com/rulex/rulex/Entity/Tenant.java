package com.rulex.rulex.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Tenant")
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "tenant_code", nullable = false,unique = true)
    private String tenantCode;

    @NotNull
    @Column(nullable = false)
    private String status;

    @NotNull
    @Column(name = "Created_At",nullable = false)
    private LocalDateTime createdAt;

}
