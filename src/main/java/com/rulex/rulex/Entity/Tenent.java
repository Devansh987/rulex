package com.rulex.rulex.Entity;


import jakarta.persistence.*;

@Entity
public class Tenent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
