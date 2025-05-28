package com.covoiturage.project.service;


import com.covoiturage.project.entity.Brand;
import com.covoiturage.project.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Brand getBrandById(Long id) {
        return  brandRepository.findBrandById(id);
    }
}