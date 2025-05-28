package com.covoiturage.project.service;

import com.covoiturage.project.entity.Car;
import com.covoiturage.project.entity.Offer;
import com.covoiturage.project.entity.Ride;
import com.covoiturage.project.entity.User;
import com.covoiturage.project.repository.OfferRepository;
import com.covoiturage.project.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private RideRepository rideRepository;

    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    public Optional<Offer> getOfferById(Long id) {
        return offerRepository.findById(id);
    }

    public Offer saveOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }

    //count offers
    public long countOffers() {
        return offerRepository.count();
    }

    // Méthode pour récupérer les 6 derniers offers
    public List<Offer> getRecentOffers() {
        return offerRepository.findTop6Offers();
    }

    //Récupérer tous les offres selon les voitures
    public List<Offer> findOffersByCars(List<Car> cars) {
        List<Offer> offers = new ArrayList<>();
        for (Car car : cars) {
            offers.addAll(offerRepository.findByCar(car));
        }
        return offers;
    }

    //chercher des offres pour le user
    public List<Offer> findMatchingOffers(Long departureId, Long destinationId, LocalDateTime date) {
        return offerRepository.findMatchingOffers(departureId, destinationId, date);
    }

    //-1 nbplaces
    public void decrementSeats(Long offerId) {
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        if (offer.getNbplaces() > 0) {
            offer.setNbplaces(offer.getNbplaces() - 1);
            offerRepository.save(offer);
        } else {
            throw new RuntimeException("No available seats");
        }
    }

    //+1 nbplaces
    public void incrementSeats(Long offerId) {
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        offer.setNbplaces(offer.getNbplaces() + 1);
        offerRepository.save(offer);
    }


}
