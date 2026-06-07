package com.rulex.rulex.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 3,max = 20)
    @Column(nullable = false, unique = true)
    private String userName;

    @Size(min = 6,message = "Password must be at least 6 letters long")
    @Column(nullable = false)
    private String password;

    @Email(message = "Invalid email format")
    @Column(nullable = false,unique = true)
    private String  email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
