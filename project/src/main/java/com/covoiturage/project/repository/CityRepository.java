package com.covoiturage.project.repository;

import com.covoiturage.project.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
    City findCityById(Long id);
}
