package com.covoiturage.project.controller.treatment;

import com.covoiturage.project.entity.City;
import com.covoiturage.project.entity.User;
import com.covoiturage.project.service.CityService;
import com.covoiturage.project.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/index")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;

    //Afficher la page index.html
    @GetMapping("/login")
    public String showLoginPage() {
        return "index"; // Retourner la page de login
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {

        // Vérifier si le username existe
        User user = userService.findUserByUsername(username);
        if (user == null) {
            model.addAttribute("usernameError", "Username does not exist !");
            return "index"; // Retourne à la page de connexion avec un message d'erreur
        }

        // Vérifier si le mot de passe est correct
        if (!user.getPassword().equals(password)) {
            model.addAttribute("passwordError", "Incorrect password !");
            return "index"; // Retourne à la page de connexion avec un message d'erreur
        }

        // Si les informations sont correctes, stocker les données dans la session
        session.setAttribute("username", username);
        session.setAttribute("userState", user.getState().name()); // Stocke l'état de l'utilisateur

        // Rediriger vers la page user.html
        return "redirect:/index/user";
    }

    //redirectionner vers la page user
    @GetMapping("/user")
    public String showUserPage(Model model) {

        //liste de tous les villes
        List<City> cities = cityService.getAllCities();
        //ajouter cities au model
        model.addAttribute("cities", cities);

        // Vérifier si des offres existent dans le modèle (lors d'une redirection)
        if (!model.containsAttribute("offers")) {
            model.addAttribute("offers", new ArrayList<>()); // Pas d'offres par défaut
        }

        return "user"; // Nom du fichier user.html
    }
}
