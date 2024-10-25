package com.dinemap.dinemap.reservations.repositories;

import com.dinemap.dinemap.reservations.entities.Models.RestaurantEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IRestaurantRepository extends MongoRepository<RestaurantEntity, ObjectId> {
    Optional<RestaurantEntity> findByRestaurantId(String id);
}
