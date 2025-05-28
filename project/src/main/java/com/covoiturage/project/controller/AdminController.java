package com.covoiturage.project.controller;

import com.covoiturage.project.entity.Admin;
import com.covoiturage.project.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/{username}")
    public Admin getAdminByUsername(@PathVariable String username) {
        return adminService.findAdminByUsername(username);
    }
}
