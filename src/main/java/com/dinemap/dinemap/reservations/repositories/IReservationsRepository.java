package com.dinemap.dinemap.reservations.repositories;

import com.dinemap.dinemap.reservations.entities.ReservationsEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IReservationsRepository extends MongoRepository<ReservationsEntity, ObjectId> {
    Optional<ReservationsEntity> findByRestaurantId (String restaurantId);
    List<ReservationsEntity> findByCreatedBy(String createdBy);
}
