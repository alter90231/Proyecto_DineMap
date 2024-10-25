package com.dinemap.dinemap.reservations.entities;

import com.dinemap.dinemap.reservations.entities.Base.BaseEntity;
import com.dinemap.dinemap.reservations.entities.Enum.ReservationsStatus;
import com.dinemap.dinemap.reservations.entities.Models.RestaurantEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "reservation")
public class ReservationsEntity extends BaseEntity {
    private String restaurantId;
    private RestaurantEntity restaurants;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    @CreatedDate
    private LocalDateTime date;
    private int numberOfGuests;
    private ReservationsStatus status = ReservationsStatus.CONFIRMED;

}
