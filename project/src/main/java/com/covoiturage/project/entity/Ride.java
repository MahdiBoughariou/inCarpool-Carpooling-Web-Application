package com.covoiturage.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_passager", nullable = false) // Clé étrangère vers la table 'user'
    private User passager;

    @ManyToOne
    @JoinColumn(name = "id_offer", nullable = false) // Clé étrangère vers la table 'offer'
    private Offer offer;
}
