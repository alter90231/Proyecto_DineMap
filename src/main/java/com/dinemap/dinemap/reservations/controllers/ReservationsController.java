package com.dinemap.dinemap.reservations.controllers;

import com.dinemap.dinemap.reservations.entities.ReservationsEntity;
import com.dinemap.dinemap.reservations.services.IReservationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/dinemap/reservations")
public class ReservationsController {
    @Autowired
    private IReservationsService reservationsService;
    @GetMapping("/mis-reservas")
    public ResponseEntity<?> getMyReservations(@RequestHeader("Authorization") String token){
        try{
            if(token.startsWith("Bearer ")){
                token = token.substring(7);
            }
            return ResponseEntity.status(HttpStatus.OK).body(reservationsService.getReservationsByLoggedUser(token));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error:\":\"Error.Por favor intente mas tarde.\"}");
        }
    }
    @PostMapping("")
    public ResponseEntity<?> createReservation(@RequestHeader("Authorization") String token, @RequestBody ReservationsEntity reservationsEntity){
        try{
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            return ResponseEntity.status(HttpStatus.OK).body(reservationsService.save(reservationsEntity, token));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error:\":\"Error.Por favor intente mas tarde.\"}");
        }
    }
    @PutMapping("/{_id}")
    public ResponseEntity<?> updateReservation(@RequestHeader("Authorization") String token,@PathVariable String _id, @RequestBody ReservationsEntity reservationsEntity){
        try{
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            return ResponseEntity.status(HttpStatus.OK).body(reservationsService.update(_id, reservationsEntity, token));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error:\":\"Error.Por favor intente mas tarde.\"}");
        }
    }
    @PutMapping("/cancelled/{_id}")
    public ResponseEntity<?> cancelledReservation(@RequestHeader("Authorization") String token, @PathVariable String _id){
        try{
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            return ResponseEntity.status(HttpStatus.OK).body(reservationsService.cancelled(_id, token));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error:\":\"Error.Por favor intente mas tarde.\"}");
        }
    }
}
