package com.dinemap.dinemap.restaurants.controllers;

import com.dinemap.dinemap.restaurants.services.IRestaurantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/dinemap/restaurant")
public class RestaurantsController {
    @Autowired
    private IRestaurantsService restaurantService;
    @GetMapping("")
    public ResponseEntity<?> getRestaurantsByUserProvince(@RequestHeader("Authorization") String token){
        try{
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            return ResponseEntity.status(HttpStatus.OK).body(restaurantService.findByDistrict(token));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error:\":\"Error.Por favor intente mas tarde.\"}");
        }
    }
}
