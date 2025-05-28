package com.covoiturage.project.controller.treatment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("profile")
public class ProfileUserController {

    @GetMapping("/profileuser")
    public String showProfilePage() {
        return "profileuser"; // Retourner la page de login
    }
}
