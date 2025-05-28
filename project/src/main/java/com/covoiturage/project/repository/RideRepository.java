package com.covoiturage.project.repository;

import com.covoiturage.project.entity.Offer;
import com.covoiturage.project.entity.Ride;
import com.covoiturage.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {

    // Méthode pour récupérer les rides associés à une offre
    List<Ride> findByOffer(Offer offer);

    //Méthode pour récupérer les rides associés à un passager (les confirmations du passager)
    List<Ride> findByPassager(User passager);

}
