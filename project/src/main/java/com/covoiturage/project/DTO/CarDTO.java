package com.covoiturage.project.DTO;

import com.covoiturage.project.entity.Brand;
import com.covoiturage.project.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {

    private String platenumber;
    private String model;
    private String state;
    private User user;
    private Brand brand;



}
