package com.dinemap.dinemap.reservations.services;

import com.dinemap.dinemap.reservations.entities.ReservationsEntity;

import java.util.List;

public interface IReservationsService {
    public List<ReservationsEntity> getReservationsByLoggedUser(String token) throws Exception;
    public ReservationsEntity save(ReservationsEntity reservationsEntity, String token) throws Exception;
    public ReservationsEntity update(String _id, ReservationsEntity reservationsEntity) throws Exception;
    public ReservationsEntity cancelled(String _id) throws Exception;
}
