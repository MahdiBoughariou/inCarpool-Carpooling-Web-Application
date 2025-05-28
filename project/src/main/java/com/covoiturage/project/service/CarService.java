package com.covoiturage.project.service;

import com.covoiturage.project.entity.Car;
import com.covoiturage.project.entity.User;
import com.covoiturage.project.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    // Nouvelle méthode pour récupérer les voitures d'un utilisateur
    public List<Car> findCarsByUser(User user) {
        return carRepository.findByUser(user);
    }

}
