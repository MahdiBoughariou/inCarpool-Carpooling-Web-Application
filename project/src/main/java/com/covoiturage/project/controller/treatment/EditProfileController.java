package com.covoiturage.project.controller.treatment;

import com.covoiturage.project.DTO.UserDTO;
import com.covoiturage.project.entity.User;
import com.covoiturage.project.service.UserService;
import com.covoiturage.project.utils.ValidationUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/editprofile")
public class EditProfileController {
    @Autowired
    private UserService userService;

    //afficher la page editprofile
    @GetMapping("/data")
    public String showDataPage(HttpSession session,Model model) {

        //récupèrer le username de la session
        String username = (String) session.getAttribute("username");
        //récupèrer les infos du user
        User user = userService.findUserByUsername(username);
        //préparer le model
        model.addAttribute("user", user);

        return "EditProfile"; // Retourner la page de EditProfile
    }

    //modifie le user
    @PostMapping("/update")
    public String updateUserProfile(@ModelAttribute UserDTO userDTO, HttpSession session, RedirectAttributes redirectAttributes) {

        //récupèrer le username de la session
        String username = (String) session.getAttribute("username");
        //récupèrer les infos du user
        User userBD = userService.findUserByUsername(username);
        //afficher userBD
        //System.out.println("userBD: " + userBD);


        //System.out.println("userDTO : " + userDTO);
        //vérifier si les champs sont vides
        if(!ValidationUtils.checkNullValuesR3(userDTO.getFirstname(), userDTO.getLastname(), userDTO.getCin(),
                userDTO.getEmail(), userDTO.getBirthdate(), userDTO.getPhone(),userDTO.getUsername())){
            redirectAttributes.addFlashAttribute("nullError", "Please fill in all the required fields !");
            return "redirect:/editprofile/data";
        }
        //vérifier l'email
        if (!ValidationUtils.verifEmail(userDTO.getEmail())) {
            redirectAttributes.addFlashAttribute("emailError", "Invalid email format (example@example.com) !");
            return "redirect:/editprofile/data";
        }
        //vérifier le cin
        if (!ValidationUtils.verifCin(userDTO.getCin())) {
            redirectAttributes.addFlashAttribute("cinError", "The number must contain exactly 8 digits !");
            return "redirect:/editprofile/data";
        }
        //vérifier le phone
        if (!ValidationUtils.verifPhoneNumber(userDTO.getPhone())) {
            redirectAttributes.addFlashAttribute("phoneError", "Invalid phone format. Use: +216-12345678 !");
            return "redirect:/editprofile/data";
        }

        // Convertir le DTO en entité User
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        //System.out.println("user après la conversion : " + user);

        if(userDTO.getPassword()==""){
            user.setId(userBD.getId());
            user.setPassword(userBD.getPassword());
            //System.out.println("user finale : " + user);

            // Mettre à jour l'utilisateur
            userService.saveUser(user);
        }
        else{
            //Vérifier si l'utilisateur a entré son ancien mdp correctement
            if(!(userBD.getPassword().equals(userDTO.getPassword()))){
                redirectAttributes.addFlashAttribute("passwordError", "Incorrect !");
                return "redirect:/editprofile/data";
            }

            if(userDTO.getPassword2()==""){
                redirectAttributes.addFlashAttribute("newPasswordError", "Enter your new password !");
                return "redirect:/editprofile/data";
            }

            //attribuer l'id et le nv mdp
            user.setId(userBD.getId());
            user.setPassword(userDTO.getPassword2());
            //System.out.println("user finale avec new mdp : " + user);

            //Mettre à jour l'utilisateur
            userService.saveUser(user);

        }
        if (!username.equals(userDTO.getUsername())) {
            session.setAttribute("username", userDTO.getUsername());
        }
        return "redirect:/index/user";
    }
}
