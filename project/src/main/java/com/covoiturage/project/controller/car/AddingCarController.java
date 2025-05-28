package com.covoiturage.project.controller.car;



import com.covoiturage.project.DTO.CarDTO;
import com.covoiturage.project.entity.Brand;
import com.covoiturage.project.entity.Car;
import com.covoiturage.project.entity.User;
import com.covoiturage.project.service.BrandService;
import com.covoiturage.project.service.CarService;
import com.covoiturage.project.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/adding")
public class AddingCarController {

    @Autowired
    private BrandService brandService;
    @Autowired
    private UserService userService;
    @Autowired
    private CarService carService;

    /*------------------------------Partie Brand------------------------------*/
    @GetMapping("/brand")
    public String showBrand() {
        return "addvehiculemarque";
    }

    @GetMapping("/showBrands")
    public String showBrands(Model model) {
        // Récupérer toutes les marques de la base de données
        List<Brand> brands = brandService.getAllBrands();
        // Ajouter les marques au modèle
        model.addAttribute("brands", brands);
        return "addvehiculemarque"; // Nom de la vue HTML
    }

    @PostMapping("/selectBrand")
    public String selectBrand(@RequestParam("brand") Long brandId, HttpSession session) {
        //Récupèrer les infos de la brand
        Brand brand = brandService.getBrandById(brandId);
        // Créez un CarDTO et stockez-le dans la session
        CarDTO carDTO = new CarDTO();
        //ajouter brand à carDTO
        carDTO.setBrand(brand);
        // Stocker dans la session
        session.setAttribute("carDTO", carDTO);
        // Rediriger vers l'étape suivante
        return "redirect:/adding/model";
    }
    /*------------------------------Partie Model------------------------------*/
    @GetMapping("/model")
    public String showModel() {
        return "addvehiculemodel";
    }

    @PostMapping("/selectModel")
    public String selectModel(@RequestParam("model") String modelName, HttpSession session) {
        // Récupérer CarDTO depuis la session
        CarDTO carDTO = (CarDTO) session.getAttribute("carDTO");
        //ajouter model à carDTO
        carDTO.setModel(modelName);
        // Rediriger vers l'étape suivante
        return "redirect:/adding/plate";
    }
    /*------------------------------Partie Plate------------------------------*/
    @GetMapping("/plate")
    public String showPlate() {
        return "addvehiculeserie";
    }

    @PostMapping("/selectPlate")
    public String selectPlate(@RequestParam("plate") String plateNumber, HttpSession session) {
        // Récupérer CarDTO depuis la session
        CarDTO carDTO = (CarDTO) session.getAttribute("carDTO");
        //ajouter plateNumber à carDTO
        carDTO.setPlatenumber(plateNumber);
        // Rediriger vers l'étape suivante
        return "redirect:/adding/state";
    }
    /*------------------------------Partie State------------------------------*/
    @GetMapping("/state")
    public String showState() {
        return "addvehiculeetat";
    }

    @PostMapping("/finalState")
    public String selectState(@RequestParam("state") String state, HttpSession session) {
        //récupèrer le username de la session
        String username = (String) session.getAttribute("username");
        //récupèrer les infos du user
        User user = userService.findUserByUsername(username);
        // Récupérer CarDTO depuis la session
        CarDTO carDTO = (CarDTO) session.getAttribute("carDTO");
        //ajouter state & user à carDTO
        carDTO.setState(state);
        carDTO.setUser(user);
        //Convertir le carDTO en car
        Car car = new Car();
        car.setPlatenumber(carDTO.getPlatenumber());
        car.setModel(carDTO.getModel());
        car.setState(carDTO.getState());
        car.setUser(user);
        car.setBrand(carDTO.getBrand());
        //Enregistrer car du user dans la base
        carService.saveCar(car);
        // Rediriger ou sauvegarder les données dans une base de données
        return "redirect:/index/user"; // Page de résumé ou confirmation
    }
}
