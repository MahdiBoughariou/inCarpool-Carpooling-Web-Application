package com.covoiturage.project.service;

import com.covoiturage.project.entity.Admin;
import com.covoiturage.project.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin findAdminByUsername(String username) {
        return adminRepository.findByUsername(username);
    }
}
