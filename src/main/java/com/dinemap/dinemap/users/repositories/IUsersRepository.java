package com.dinemap.dinemap.users.repositories;

import com.dinemap.dinemap.users.entities.UsersEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IUsersRepository extends MongoRepository<UsersEntity, ObjectId> {
    Optional<UsersEntity> findByUsername(String username);
    Optional<UsersEntity> findByEmail(String email);
}
