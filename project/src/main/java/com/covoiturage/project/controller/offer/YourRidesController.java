package com.covoiturage.project.controller.offer;

import com.covoiturage.project.entity.Ride;
import com.covoiturage.project.entity.User;
import com.covoiturage.project.service.OfferService;
import com.covoiturage.project.service.RideService;
import com.covoiturage.project.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/rides")
public class YourRidesController {
    @Autowired
    UserService userService;
    @Autowired
    RideService rideService;
    @Autowired
    OfferService offerService;

    @GetMapping("/yourRides")
    public String yourOffers(Model model, HttpSession session) {

        //récupèrer le username de la session
        String username = (String) session.getAttribute("username");
        //récupèrer les infos du user
        User passager = userService.findUserByUsername(username);

        List<Ride> rides = rideService.findRidesByPassager(passager);
        //System.out.println("rides: "+rides);

        //ajouter rides au model
        model.addAttribute("rides", rides);

        return "yourRides";
    }

    //cancel ride
    @PostMapping("/cancelRide")
    public String cancelRide(
            @RequestParam("rideId") Long rideId,
            @RequestParam("offerId") Long offerId,
            RedirectAttributes redirectAttributes) {

        // Supprimer le Ride
        rideService.deleteRide(rideId);

        // Incrémenter le nombre de places de l'offre
        offerService.incrementSeats(offerId);

        return "redirect:/rides/yourRides"; // Retourner à la page des rides
    }


}
