package com.covoiturage.project.repository;
import com.covoiturage.project.entity.Car;
import com.covoiturage.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByPlatenumber(String platenumber);
    // Méthode pour récupérer toutes les voitures d'un utilisateur
    List<Car> findByUser(User user);

}
