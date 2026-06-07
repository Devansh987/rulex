package com.rulex.rulex.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
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


}
