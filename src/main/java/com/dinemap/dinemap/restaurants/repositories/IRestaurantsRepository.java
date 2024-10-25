package com.dinemap.dinemap.restaurants.repositories;

import com.dinemap.dinemap.restaurants.entities.RestaurantEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IRestaurantsRepository extends MongoRepository<RestaurantEntity, ObjectId> {
    List<RestaurantEntity> findByDistrict(String district);
}
