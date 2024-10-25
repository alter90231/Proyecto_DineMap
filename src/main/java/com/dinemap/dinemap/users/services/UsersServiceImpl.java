package com.dinemap.dinemap.users.services;

import com.dinemap.dinemap.users.entities.Log.AuditEntity;
import com.dinemap.dinemap.users.entities.Login.LoginRequest;
import com.dinemap.dinemap.users.entities.UsersEntity;
import com.dinemap.dinemap.users.repositories.IAuditRepository;
import com.dinemap.dinemap.users.repositories.IUsersRepository;
import com.dinemap.dinemap.users.token.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements IUsersService{
    @Autowired
    private IUsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private IAuditRepository auditRepository;
    @Override
    public UsersEntity register(UsersEntity usersEntity) throws Exception {
        try{
            if(usersRepository.findByUsername(usersEntity.getUsername()).isPresent()){
                throw new Exception("Username already exists");
            }
            if(usersRepository.findByEmail(usersEntity.getEmail()).isPresent()){
                throw new Exception("Email already exists");
            }
            String hashedPasswrord = passwordEncoder.encode(usersEntity.getPassword());
            usersEntity.setPassword(hashedPasswrord);
            UsersEntity savedUser = usersRepository.save(usersEntity);
            auditRepository.save(new AuditEntity("Registro de usuario", usersEntity.getUsername(), "El usuario se registro correctamente"));
            return savedUser;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public String authenticate(LoginRequest loginRequest) throws Exception {
        try{
            UsersEntity usersEntity = usersRepository.findByUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            if(passwordEncoder.matches(loginRequest.getPassword(), usersEntity.getPassword())){
                auditRepository.save(new AuditEntity("Logueo de Usuario", loginRequest.getUsername(), "El usuario se logueo Correctamente"));

                return jwtTokenProvider.generateToken(usersEntity.getUsername());
            }else{
                throw new RuntimeException("Invalid credentials");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
}
