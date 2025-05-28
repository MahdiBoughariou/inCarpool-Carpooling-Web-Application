package com.covoiturage.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class User {

    public enum UserState {
        NOT_QUALIFIED,
        APPROVED,
        NOT_APPROVED,
        PENDING
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String firstname;

    @Column(nullable = false, length = 50)
    private String lastname;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false)
    private String cin;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING) // Stocke les valeurs sous forme de chaînes (ex: "NOT_QUALIFIED")
    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'NOT_QUALIFIED'")
    private UserState state = UserState.NOT_QUALIFIED;

    @Column(columnDefinition = "VARCHAR(255) DEFAULT NULL")
    private String imglic;

    @Column(columnDefinition = "VARCHAR(255) DEFAULT NULL")
    private  String imgcin;

    /*@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car> cars = new ArrayList<>();*/

}
