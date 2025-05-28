package com.covoiturage.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String platenumber;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false) // Clé étrangère vers User
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_brand", nullable = false) // Clé étrangère vers Brand
    private Brand brand;
}
