package com.covoiturage.project.DTO;

import com.covoiturage.project.entity.Car;
import com.covoiturage.project.entity.City;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class OfferDTO {
    private City departure;
    private City destination;
    private LocalDateTime date;
    private LocalTime time;
    private Car car;
    private Integer nbplaces;
    private String price;
    private String comment;
}
