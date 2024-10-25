package com.dinemap.dinemap.restaurants.services;

import com.dinemap.dinemap.restaurants.entities.RestaurantEntity;

import java.util.List;

public interface IRestaurantsService {
    public List<RestaurantEntity> findByDistrict(String district) throws Exception;

}
