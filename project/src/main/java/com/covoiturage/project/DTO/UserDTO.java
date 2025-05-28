package com.covoiturage.project.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {
    private String firstname;
    private String lastname;
    private String cin;
    private String email;
    private LocalDate birthdate;
    private String phone;
    private String username;
    private String password;
    private String password2;
}
