package com.rulex.rulex.Entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name = "Policy")
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
