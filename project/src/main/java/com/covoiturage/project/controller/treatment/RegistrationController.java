package com.covoiturage.project.controller.treatment;

import com.covoiturage.project.DTO.UserDTO;
import com.covoiturage.project.utils.ValidationUtils;
import com.covoiturage.project.entity.User;
import com.covoiturage.project.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    // Vérifier si le username existe
    @GetMapping("/CheckUsernameAvailability")
    @ResponseBody
    public String checkUsernameAvailability(@RequestParam String username) {
        // Vérifiez si le nom d'utilisateur existe dans la base de données
        boolean usernameExists = userService.usernameExists(username);

        if (usernameExists) {
            return "Username not available, try another one!";
        } else {
            return "Username available.";
        }
    }

    // Vérifier si l'email existe
    @GetMapping("/CheckEmailAvailability")
    @ResponseBody
    public String checkEmailAvailability(@RequestParam String email) {

        // Vérifie si l'email existe dans la base de données
        boolean emailExists = userService.emailExists(email);

        if (emailExists) {
            return "Email already in use, try another one!";
        } else {
            return "Email available.";
        }
    }

    // Étape 1 : Afficher le premier formulaire
    @GetMapping("/step1")
    public String showStep1(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "register";
    }

    // Étape 1 : Traiter les données du premier formulaire
    @PostMapping("/step1")
    public String handleStep1(@ModelAttribute UserDTO userDTO, HttpSession session, Model model) {

        // Vérifications des champs
        if (!ValidationUtils.checkNullValuesR1(userDTO.getFirstname(), userDTO.getLastname(), userDTO.getCin(),
                userDTO.getEmail(), userDTO.getBirthdate(), userDTO.getPhone())) {
            model.addAttribute("nullError", "Please fill in all the required fields !");
            return "register";
        }

        if (!ValidationUtils.verifEmail(userDTO.getEmail())) {
            model.addAttribute("emailError", "Invalid email format (example@example.com) !");
            return "register";
        }

        if (!ValidationUtils.verifCin(userDTO.getCin())) {
            model.addAttribute("cinError", "The number must contain exactly 8 digits !");
            return "register";
        }

        if (!ValidationUtils.verifPhoneNumber(userDTO.getPhone())) {
            model.addAttribute("phoneError", "Invalid phone format. Use: +216-12345678 !");
            return "register";
        }

        // Stocker les données dans la session
        session.setAttribute("userDTO", userDTO);
        return "redirect:/registration/step2"; // Rediriger vers le deuxième formulaire
    }

    // Étape 2 : Afficher le deuxième formulaire
    @GetMapping("/step2")
    public String showStep2(HttpSession session, Model model) {
        // Récupérer les données de l'étape 1 depuis la session
        UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");
        if (userDTO == null) {
            return "redirect:/registration/step1"; // Retourner à l'étape 1 si les données sont manquantes
        }
        model.addAttribute("userDTO", userDTO);
        return "registerp2";
    }

    // Étape 2 : Traiter les données du deuxième formulaire
    @PostMapping("/step2")
    public String handleStep2(@ModelAttribute UserDTO userDTO, HttpSession session, Model model) {

        // Vérifications des champs
        if (!ValidationUtils.checkNullValuesR2(userDTO.getUsername(),userDTO.getPassword(), userDTO.getPassword2())) {
            model.addAttribute("nullError", "Please fill in all the required fields !");
            return "registerp2";
        }

        if(!ValidationUtils.verifSamePassword(userDTO.getPassword(),userDTO.getPassword2())){
            model.addAttribute("passwordError", "Please enter same passwords !");
            return "registerp2";
        }

        // Compléter les données avec celles de l'étape 1
        UserDTO step1Data = (UserDTO) session.getAttribute("userDTO");

        if (step1Data != null) {

            step1Data.setUsername(userDTO.getUsername());
            step1Data.setPassword(userDTO.getPassword());

            // Enregistrer l'utilisateur dans la base de données (exemple)
            UserDTO user1dto = (UserDTO) step1Data;

            // Convertir le DTO en entité User
            User user = new User();
            BeanUtils.copyProperties(user1dto, user);

            // Sauvegarder l'utilisateur
            userService.saveUser(user);
        }
        session.removeAttribute("userDTO"); // Nettoyer la session

        return "redirect:/registration/index"; // Rediriger vers la page de succès
    }

    // Page de index
    @GetMapping("/index")
    public String showIndexPage() {
        //model.addAttribute("succes","Account created successfully.");
        return "index";
    }
}
