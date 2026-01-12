package com.example.Directory.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = "phone")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(nullable = false, unique = true)
    private String phone;

    private LocalDateTime birthDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean canAddProducts;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Product> products;
}
