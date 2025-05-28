package com.covoiturage.project.controller.offer;

import com.covoiturage.project.entity.Car;
import com.covoiturage.project.entity.Offer;
import com.covoiturage.project.entity.Ride;
import com.covoiturage.project.entity.User;
import com.covoiturage.project.service.CarService;
import com.covoiturage.project.service.OfferService;
import com.covoiturage.project.service.RideService;
import com.covoiturage.project.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/offers")
public class YourOffersController {

    @Autowired
    UserService userService;
    @Autowired
    CarService carService;
    @Autowired
    OfferService offerService;
    @Autowired
    RideService rideService;

    @GetMapping("/yourOffers")
    public String yourOffers(Model model, HttpSession session) {

        //récupèrer le username de la session
        String username = (String) session.getAttribute("username");
        //récupèrer les infos du user
        User user = userService.findUserByUsername(username);
        //récupérer les voitures de user
        List<Car> cars = carService.findCarsByUser(user);
        // Récupérer toutes les offres associées à ces voitures
        List<Offer> offers = offerService.findOffersByCars(cars);

        //récupérer les passages qui ont confirmé (rides) (parcourir offre par offre et récupérer les rides)
        Map<Long, List<Ride>> ridesMap = new HashMap<>();
        for (Offer offer : offers) {
            List<Ride> rides = rideService.findRidesByOffer(offer);
            ridesMap.put(offer.getId(), rides);
        }

        // Ajouter les offres et la map des rides au modèle
        model.addAttribute("offers", offers);
        model.addAttribute("ridesMap", ridesMap);

        return "yourOffers";
    }
}