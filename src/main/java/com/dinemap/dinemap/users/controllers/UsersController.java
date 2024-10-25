package com.dinemap.dinemap.users.controllers;

import com.dinemap.dinemap.users.entities.Login.LoginRequest;
import com.dinemap.dinemap.users.entities.UsersEntity;
import com.dinemap.dinemap.users.services.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/dinemap/users")
public class UsersController {
    @Autowired
    private IUsersService usersService;
    @PostMapping("")
    public ResponseEntity<?> register(@RequestBody UsersEntity usersEntity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(usersService.register(usersEntity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("\"{\"error:\":\"Error.Por favor intente mas tarde.\"");
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(usersService.authenticate(loginRequest));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("\"{\"error:\":\"Error.Por favor intente mas tarde.\"");
        }
    }
}
