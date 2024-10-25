package com.dinemap.dinemap.reservations.services;

import com.dinemap.dinemap.reservations.entities.Enum.ReservationsStatus;
import com.dinemap.dinemap.reservations.entities.Models.RestaurantEntity;
import com.dinemap.dinemap.reservations.entities.ReservationsEntity;
import com.dinemap.dinemap.reservations.repositories.IReservationsRepository;
import com.dinemap.dinemap.reservations.repositories.IRestaurantRepository;
import com.dinemap.dinemap.users.services.IUsersService;
import com.dinemap.dinemap.users.token.JwtTokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
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
    @Autowired
    private IUsersService usersService;
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
            String emailUser = usersService.getUserEmail(token);
            String phoneUser = usersService.getUserPhone(token);
            reservationsEntity.setCreatedBy(username);
            reservationsEntity.setCustomerEmail(emailUser);
            reservationsEntity.setCustomerPhone(phoneUser);
            RestaurantEntity restaurantEntity = restaurantRepository.findByRestaurantId(reservationsEntity.getRestaurantId())
                            .orElseThrow(() -> new Exception("Restaurant not found")) ;
            reservationsEntity.setRestaurants(restaurantEntity);
            return reservationsRepository.save(reservationsEntity);

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ReservationsEntity update(String _id, ReservationsEntity reservationsEntity, String token) throws Exception {
        try{
            ObjectId objectId = new ObjectId(_id);
            String username;
            try{
                username = jwtTokenProvider.getUsernameFromToken(token);
            } catch (SignatureException e) {
                throw new Exception("Invalid JWT signature: " + e.getMessage());
            } catch (ExpiredJwtException e) {
                throw new Exception("JWT token has expired: " + e.getMessage());
            } catch (Exception e) {
                throw new Exception("JWT validation error: " + e.getMessage());
            }
            ReservationsEntity existingReservation = reservationsRepository.findById(objectId)
                    .orElseThrow(() -> new Exception("Reservation not found"));

            if(!existingReservation.getCreatedBy().equals(username)){
                throw new Exception("Unauthorized: You can only update your own reservations.");
            }
            RestaurantEntity restaurantEntity = restaurantRepository.findByRestaurantId(reservationsEntity.getRestaurantId())
                    .orElseThrow(() -> new Exception("Restaurant not found"));

            existingReservation.setRestaurants(restaurantEntity);
            existingReservation.setCustomerName(reservationsEntity.getCustomerName());
            existingReservation.setNumberOfGuests(reservationsEntity.getNumberOfGuests());
            existingReservation.setUpdateBy(username);

            return reservationsRepository.save(existingReservation);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    public ReservationsEntity cancelled(String _id, String token) throws Exception {
        try{
            ObjectId objectId = new ObjectId(_id);
            String username = jwtTokenProvider.getUsernameFromToken(token);

            ReservationsEntity existingReservation = reservationsRepository.findById(objectId)
                    .orElseThrow(() -> new Exception("Reservation not found"));

            if(!existingReservation.getCreatedBy().equals(username)){
                throw new Exception("Unauthorized: You can only cancel your own reservations.");
            }

            existingReservation.setStatus(ReservationsStatus.CANCELLED);
            return reservationsRepository.save(existingReservation);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
