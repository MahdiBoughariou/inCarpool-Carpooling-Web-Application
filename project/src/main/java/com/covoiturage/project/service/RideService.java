package com.covoiturage.project.service;

import com.covoiturage.project.entity.Offer;
import com.covoiturage.project.entity.Ride;
import com.covoiturage.project.entity.User;
import com.covoiturage.project.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {
    @Autowired
    private RideRepository rideRepository;

    //Counting reservations
    public long countReservations() {
        return rideRepository.count();
    }

    //récupérer les rides du offer
    public List<Ride> findRidesByOffer(Offer offer) {
        return rideRepository.findByOffer(offer);
    }

    //écupérer les rides du passager
    public List<Ride> findRidesByPassager(User passager) {
        return rideRepository.findByPassager(passager);
    }

    public void addRide(Offer offer, User passager) {
        Ride ride = new Ride();
        ride.setOffer(offer);
        ride.setPassager(passager);
        rideRepository.save(ride);
    }

    public void deleteRide(Long rideId) {
        rideRepository.deleteById(rideId);
    }
}
