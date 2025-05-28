package com.covoiturage.project.controller.treatment;
import com.covoiturage.project.entity.User;

import com.covoiturage.project.entity.User;
import com.covoiturage.project.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.desktop.SystemEventListener;

@Controller
@RequestMapping("/upgrade")
public class UpgradeProfileController {

    @Autowired
    private UserService userService;

    //afficher la page de l' upgrade
    @GetMapping("/upgradeprofile")
    public String showUpgradePage() {
        return "UpgradeProfile";
    }

    //récupérer les fichiers
    @PostMapping("/submit")
    public String handleFileUpload(@RequestParam("imgcin") MultipartFile imgCin, @RequestParam("imglic") MultipartFile imgLic, HttpSession session, RedirectAttributes redirectAttributes) {

        //récupèrer le username de la session
        String username = (String) session.getAttribute("username");

        //récupèrer les infos du user
        User userBD = userService.findUserByUsername(username);

        // Récupérer les noms des fichiers
        String imgCinFileName = imgCin.getOriginalFilename();
        String imgLicFileName = imgLic.getOriginalFilename();

        // Vérifier si les deux fichiers sont présents
        if (imgCin.isEmpty() || imgLic.isEmpty()) {
            redirectAttributes.addFlashAttribute("filesError", "Both files are required.");
            return "redirect:/upgrade/upgradeprofile";
        }

        // Vérifier si les deux fichiers sont au format PDF
        if (!imgCin.getOriginalFilename().toLowerCase().endsWith(".pdf") ||
                !imgLic.getOriginalFilename().toLowerCase().endsWith(".pdf")) {
            redirectAttributes.addFlashAttribute("pdfError", "Both files must be in PDF format.");
            return "redirect:/upgrade/upgradeprofile";
        }

        userBD.setImgcin(imgCinFileName);
        userBD.setImglic(imgLicFileName);
        userBD.setState(User.UserState.PENDING);

        //System.out.println("userBD: "+ userBD);

        //Mettre à jour l'utilisateur
        userService.saveUser(userBD);

        return "redirect:/index/user"; // Rediriger vers une page de profil après le succès
    }


}