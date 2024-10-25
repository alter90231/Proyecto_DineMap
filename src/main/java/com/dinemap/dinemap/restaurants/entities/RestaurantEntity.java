package com.dinemap.dinemap.restaurants.entities;

import com.dinemap.dinemap.restaurants.entities.Models.MenuSummary;
import com.dinemap.dinemap.restaurants.entities.Models.Schedule;
import com.dinemap.dinemap.restaurants.entities.Models.SocialMedia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "restaurant")
public class RestaurantEntity {
    private String restaurantId;
    private String name;
    private String description;
    private List<String> category;
    private String address;
    private String district;
    private String phone;
    private String email;
    private List<Schedule> schedule;
    private int capacity;
    private List<String> services;
    private double rating;
    private List<MenuSummary> menu_resumen;
    private SocialMedia socialMedia;
}
