package com.dinemap.dinemap.users.services;

import com.dinemap.dinemap.users.entities.Login.LoginRequest;
import com.dinemap.dinemap.users.entities.UsersEntity;

public interface IUsersService {
    public UsersEntity register(UsersEntity usersEntity) throws Exception;
    public String authenticate(LoginRequest loginRequest) throws Exception;
    public String getUserProvince(String token) throws Exception;
    public String getUserEmail(String token) throws Exception;
    public String getUserPhone(String token) throws Exception;
}
