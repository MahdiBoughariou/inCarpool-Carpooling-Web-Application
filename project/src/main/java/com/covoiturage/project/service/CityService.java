package com.covoiturage.project.service;

import com.covoiturage.project.entity.City;
import com.covoiturage.project.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;
    public List<City> getAllCities(){
        return cityRepository.findAll();
    }
    public City getCityById(Long id){return cityRepository.findCityById(id);}

}
