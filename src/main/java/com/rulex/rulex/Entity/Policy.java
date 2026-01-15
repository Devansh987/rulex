package com.rulex.rulex.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Policy")
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;
    private String policyCode;
    private String description;
    private String Status;
    private Tenant tenant;
    private LocalDateTime created_at;

}
