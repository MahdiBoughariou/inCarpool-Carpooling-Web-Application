package com.covoiturage.project.controller.administration;

import com.covoiturage.project.entity.Admin;
import com.covoiturage.project.entity.Offer;
import com.covoiturage.project.entity.User;
import com.covoiturage.project.service.AdminService;
import com.covoiturage.project.service.OfferService;
import com.covoiturage.project.service.RideService;
import com.covoiturage.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class Administrator {

    @Autowired
    AdminService adminService;
    @Autowired
    UserService userService;
    @Autowired
    OfferService offerService;
    @Autowired
    private RideService rideService;

    /*------------------------------Partie Login------------------------------*/
    @GetMapping("/showLoginA")
    public String showLoginA() {
        return "loginA";
    }

    @PostMapping("/loginA")
    public String loginAdmin(@RequestParam String username, @RequestParam String password, Model model) {
        //récupérer le admin
        Admin admin = adminService.findAdminByUsername(username);
        if(admin == null) {
            model.addAttribute("usernameError", "Username does not exist !");
            return "loginA";
        }
        if(!(admin.getPassword().equals(password))) {
            model.addAttribute("passwordError", "Incorrect password !");
            return "loginA";
        }
        return "redirect:/admin/indexA";
    }
    /*------------------------------Partie index de l'admin------------------------------*/
    @GetMapping("/indexA")
    public String indexA(Model model) {

        //recent Customers (10 derniers)
        List<User> recentUsers = userService.getRecentUsers();
        model.addAttribute("recentUsers", recentUsers);

        //recent offers (6 derniers)
        List<Offer> recentOffers = offerService.getRecentOffers();
        model.addAttribute("recentOffers", recentOffers);

        //counting offers
        long offerCount = offerService.countOffers();
        model.addAttribute("offerCount", offerCount);

        //counting users
        long userCount = userService.countUsers();
        model.addAttribute("userCount", userCount);

        //counting reservations
        long reservationCount = rideService.countReservations();
        model.addAttribute("reservationCount", reservationCount);

        return "indexA";
    }

    /*------------------------------Partie customers pending,approved,not approved------------------------------*/
    @GetMapping("/showCustomers")
    public String showCustomers(Model model) {
        //récupérer tous les users sauf les users avec le state notQualified
        List<User> users = userService.getUsersWithStateNotQualified();
        //ajouter les users au model
        model.addAttribute("users", users);
        return "customers";
    }

    @GetMapping("/showRequests")
    public String showRequests(Model model) {
        //récupérer tous les users avec le state pending
        List<User> users = userService.getUsersWithStatePending();
        //ajouter les users au model
        model.addAttribute("users", users);
        return "customerRequest";
    }
    @PostMapping("/confirm/{id}")
    public String confirmUser(@PathVariable Long id) {
        userService.updateUserState(id,"APPROVED");
        return "redirect:/admin/showRequests"; // Redirige vers la liste des utilisateurs
    }

    @PostMapping("/reject/{id}")
    public String rejectUser(@PathVariable Long id) {
        userService.updateUserState(id,"NOT_APPROVED");
        return "redirect:/admin/showRequests"; // Redirige vers la liste des utilisateurs
    }

}
