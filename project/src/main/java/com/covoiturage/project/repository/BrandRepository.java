package com.covoiturage.project.repository;

import com.covoiturage.project.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findBrandById(Long id);
}
