package com.covoiturage.project.repository;

import com.covoiturage.project.entity.Car;
import com.covoiturage.project.entity.Offer;
import com.covoiturage.project.entity.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    // Récupérer les 10 derniers utilisateurs
    default List<Offer> findTop6Offers() {
        Pageable pageable = PageRequest.of(0, 6);
        return findAllByOrderByIdDesc(pageable);
    }

    List<Offer> findAllByOrderByIdDesc(Pageable pageable);

    //récupérer tous les offres avec les voitures du user
    List<Offer> findByCar(Car car);

    //cherche des offres pour le user
    @Query("SELECT o FROM Offer o WHERE " +
            "o.departure.id = :departureId AND " +
            "o.destination.id = :destinationId AND " +
            "o.date >= :date AND " +
            "o.nbplaces > 0 AND " +
            "o.state = 'SCHEDULED'")
    List<Offer> findMatchingOffers(
            @Param("departureId") Long departureId,
            @Param("destinationId") Long destinationId,
            @Param("date") LocalDateTime date
    );

}
