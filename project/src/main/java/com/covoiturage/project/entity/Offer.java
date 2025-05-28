package com.covoiturage.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Offer {

    public enum OfferState {
        SCHEDULED,
        ONGOING,
        COMPLETED,
        CANCELLED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_departure", nullable = false)
    private City departure;

    @ManyToOne
    @JoinColumn(name = "id_destination", nullable = false)
    private City destination;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "id_car", nullable = false) // Clé étrangère vers la table 'Car'
    private Car car;

    @Column(nullable = false)
    private Integer nbplaces;

    @Column(nullable = false)
    private String price;

    @Column
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'SCHEDULED'")
    private OfferState state = OfferState.SCHEDULED;
}
