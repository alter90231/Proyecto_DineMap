package com.dinemap.dinemap.reservations.services;

import com.dinemap.dinemap.reservations.entities.Enum.ReservationsStatus;
import com.dinemap.dinemap.reservations.entities.Models.RestaurantEntity;
import com.dinemap.dinemap.reservations.entities.ReservationsEntity;
import com.dinemap.dinemap.reservations.repositories.IReservationsRepository;
import com.dinemap.dinemap.reservations.repositories.IRestaurantRepository;
import com.dinemap.dinemap.users.token.JwtTokenProvider;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationsServiceImpl implements IReservationsService{
    @Autowired
    private IReservationsRepository reservationsRepository;
    @Autowired
    private IRestaurantRepository restaurantRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Override
    public List<ReservationsEntity> getReservationsByLoggedUser(String token) throws Exception {
        try{
            String username = jwtTokenProvider.getUsernameFromToken(token);
            List<ReservationsEntity> reservationsEntities = reservationsRepository.findByCreatedBy(username);

            if(reservationsEntities.isEmpty()){
                throw new Exception("No reservations found for the user");
            }
            return reservationsEntities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ReservationsEntity save(ReservationsEntity reservationsEntity, String token) throws Exception {
        try{
            String username = jwtTokenProvider.getUsernameFromToken(token);
            reservationsEntity.setCreatedBy(username);

            RestaurantEntity restaurantEntity = restaurantRepository.findByRestaurantId(reservationsEntity.getRestaurantId())
                            .orElseThrow(() -> new Exception("Restaurant not found")) ;
            reservationsEntity.setRestaurants(restaurantEntity);
            return reservationsRepository.save(reservationsEntity);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ReservationsEntity update(String _id, ReservationsEntity reservationsEntity) throws Exception {
        try{
            ObjectId objectId = new ObjectId(_id);
            ReservationsEntity existingReservation = reservationsRepository.findById(objectId)
                    .orElseThrow(() -> new Exception("Reservation not found"));
            RestaurantEntity restaurantEntity = restaurantRepository.findByRestaurantId(reservationsEntity.getRestaurantId())
                    .orElseThrow(() -> new Exception("Restaurant not found"));

            Optional<ReservationsEntity> entityOptional = reservationsRepository.findById(objectId);
            ReservationsEntity entityUpdate = entityOptional.get();
            reservationsEntity.set_id(objectId);
            reservationsEntity.setDate(existingReservation.getDate());
            reservationsEntity.setCreatedAt(existingReservation.getCreatedAt());
            reservationsEntity.setRestaurants(restaurantEntity);
            entityUpdate = reservationsRepository.save(reservationsEntity);
            return entityUpdate;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    public ReservationsEntity cancelled(String _id) throws Exception {
        try{
            ObjectId objectId = new ObjectId(_id);
            ReservationsEntity existingReservation = reservationsRepository.findById(objectId)
                    .orElseThrow(() -> new Exception("Reservation not found"));
            Optional<ReservationsEntity> reservationsEntity = reservationsRepository.findById(objectId);
            ReservationsEntity reservations = reservationsEntity.get();
            reservations.setStatus(ReservationsStatus.CANCELLED);
            return reservationsRepository.save(reservations);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
