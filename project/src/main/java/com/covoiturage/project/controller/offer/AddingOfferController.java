package com.covoiturage.project.controller.offer;

import com.covoiturage.project.DTO.OfferDTO;
import com.covoiturage.project.entity.*;
import com.covoiturage.project.service.CarService;
import com.covoiturage.project.service.CityService;
import com.covoiturage.project.service.OfferService;
import com.covoiturage.project.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/offer")
public class AddingOfferController {

    @Autowired
    private CityService cityService;
    @Autowired
    private UserService userService;
    @Autowired
    private CarService carService;
    @Autowired
    private OfferService offerService;

    /*------------------------------Partie Location------------------------------*/
    //1
    @GetMapping("/location")
    public String showLocation(){
        return "publisharidelocation";
    }

    @GetMapping("/showCities")
    public String showCities(Model model) {
        // Récupérer toutes les villes
        List<City> cities = cityService.getAllCities();
        model.addAttribute("cities", cities); // Ajouter les villes au modèle
        return "publisharidelocation"; // Nom de la vue HTML
    }

    @PostMapping("/selectCities")
    public String selectCities(@RequestParam("departure") Long departureID, @RequestParam("destination") Long destinationID, HttpSession session){
        //récupérer les infos du dep et des
        City departure = cityService.getCityById(departureID);
        City destination = cityService.getCityById(destinationID);
        //créer un offre dto
        OfferDTO offerDTO = new OfferDTO();
        //ajouter dep & des à offreDTO
        offerDTO.setDeparture(departure);
        offerDTO.setDestination(destination);
        //stocker dans la session
        session.setAttribute("offerDTO", offerDTO);
        // Rediriger vers l'étape suivante
        return "redirect:/offer/date";
    }
    /*------------------------------Partie Date------------------------------*/
    //2
    @GetMapping("/date")
    public String showDate(){
        return "publisharidedate";
    }

    @PostMapping("/selectDate")
    public String selectDate(@RequestParam("date") LocalDateTime date ,HttpSession session){
        //Récupérer le offerDTO de la session
        OfferDTO offerDTO = (OfferDTO) session.getAttribute("offerDTO");
        //ajouter la date à offerDTO
        offerDTO.setDate(date);
        // Rediriger vers l'étape suivante
        return "redirect:/offer/showCars";

    }
    /*------------------------------Partie Vehicle------------------------------*/
    //3
    @GetMapping("/vehicle")
    public String showVehicle(){
        return "publisharidevehicule";
    }

    @GetMapping("/showCars")
    public String showCars(Model model, HttpSession session){
            // Récupérer le nom d'utilisateur
            String username = (String) session.getAttribute("username");
            // Récupérer les informations de l'utilisateur
            User user = userService.findUserByUsername(username);
            // Récupérer les voitures de l'utilisateur
            List<Car> cars = carService.findCarsByUser(user);
            // Ajouter les voitures au modèle
            model.addAttribute("cars", cars);
            return "publisharidevehicule";
    }

    @PostMapping("/selectCar")
    public String selectCar(@RequestParam("car") Long carID, HttpSession session){
        //récupérer les infos de la voiture
        Car car = carService.getCarById(carID);
        //récupérer le offerDTO de la session
        OfferDTO offerDTO = (OfferDTO) session.getAttribute("offerDTO");
        //ajouter la voiture à offerDTO
        offerDTO.setCar(car);
        // Rediriger vers l'étape suivante
        return "publisharidenbplace";
    }
    /*------------------------------Partie nbplace------------------------------*/
    //4
    @GetMapping("/nbplace")
    public String showNbplace(){
        return "publisharidenbplace";
    }

    @PostMapping("/selectNbplace")
    public String selectNbplace(@RequestParam("nbplace") Integer nbplace, HttpSession session){
        //récupérer l'offreDTO de la session
        OfferDTO offerDTO = (OfferDTO) session.getAttribute("offerDTO");
        //ajouter nbplace à l'offreDTO
        offerDTO.setNbplaces(nbplace);
        // Rediriger vers l'étape suivante
        return "publisharideprix";
    }
    /*------------------------------Partie price------------------------------*/
    //5
    @GetMapping("/price")
    public String showPrice(){
        return "publisharideprix";
    }

    @PostMapping("/selectPrice")
    public String selectPrice(@RequestParam("price") String price, HttpSession session){
        //récupérer l'offreDTO de la session
        OfferDTO offerDTO = (OfferDTO) session.getAttribute("offerDTO");
        //ajouter price à l'offreDTO
        offerDTO.setPrice(price);
        // Rediriger vers l'étape suivante
        return "publisharidetime";
    }

    /*------------------------------Partie time------------------------------*/
    //6
    @GetMapping("/time")
    public String showTime(){
        return "publisharidetime";
    }

    @PostMapping("/selectTime")
    public String selectTime(@RequestParam("time") LocalTime time, HttpSession session){
        //récupérer l'offreDTO de la session
        OfferDTO offerDTO = (OfferDTO) session.getAttribute("offerDTO");
        //ajouter time à l'offreDTO
        offerDTO.setTime(time);
        // Rediriger vers l'étape suivante
        return "publisharidecomment";
    }
    /*------------------------------Partie comment------------------------------*/
    //7
    @GetMapping("/comment")
    public String showComment(){
        return "publisharidecomment";
    }

    @PostMapping("/selectComment")
    public String selectComment(@RequestParam("comment") String comment, HttpSession session){
        //récupérer l'offreDTO de la session
        OfferDTO offerDTO = (OfferDTO) session.getAttribute("offerDTO");
        //ajouter comment à l'offreDTO
        offerDTO.setComment(comment);
        //créer un offer à partir du offerDTO
        Offer offer = new Offer();
        offer.setDeparture(offerDTO.getDeparture());
        offer.setDestination(offerDTO.getDestination());
        offer.setDate(offerDTO.getDate());
        offer.setTime(offerDTO.getTime());
        offer.setCar(offerDTO.getCar());
        offer.setNbplaces(offerDTO.getNbplaces());
        offer.setPrice(offerDTO.getPrice());
        offer.setComment(offerDTO.getComment());
        //Savgarder le offre dans la base
        offerService.saveOffer(offer);
        return "redirect:/index/user";
    }
}
