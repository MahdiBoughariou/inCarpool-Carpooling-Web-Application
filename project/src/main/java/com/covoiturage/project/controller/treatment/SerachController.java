package com.covoiturage.project.controller.treatment;

import com.covoiturage.project.entity.Offer;
import com.covoiturage.project.entity.User;
import com.covoiturage.project.service.OfferService;
import com.covoiturage.project.service.RideService;
import com.covoiturage.project.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/search")
public class SerachController {

    @Autowired
    OfferService offerService;
    @Autowired
    private UserService userService;
    @Autowired
    private RideService rideService;

    @PostMapping("/searchOffer")
    public String selectSearch(
            @RequestParam("departure") Long departureId,
            @RequestParam("destination") Long destinationId,
            @RequestParam("date") LocalDateTime date,
            RedirectAttributes redirectAttributes){

        // Rechercher les offres correspondantes
        List<Offer> offers = offerService.findMatchingOffers(departureId, destinationId, date);
        System.out.println("offers for user: "+offers);
        // Ajouter les offres aux attributs de redirection
        redirectAttributes.addFlashAttribute("offers", offers);

        return "redirect:/index/user";
    }

    //confirmer un offer + décrémenter le nbplaces
    @PostMapping("/confirmOffer")
    public String confirmOffer(@RequestParam("offerId") Long offerId, RedirectAttributes redirectAttributes, HttpSession session){

        //récupèrer le username de la session
        String username = (String) session.getAttribute("username");

        //récupèrer les infos du user
        User passager = userService.findUserByUsername(username);

        //récupérer les infos de l'offre
        Optional<Offer> offer = offerService.getOfferById(offerId);
        Offer offer1 = (Offer) offer.get();

        //enregistrer dans la base
        rideService.addRide(offer1,passager);

        //Décrémenter le nbplaces de l'offre
        offerService.decrementSeats(offerId);

        return "redirect:/rides/yourRides";
    }

}
