package com.dinemap.dinemap.users.repositories;

import com.dinemap.dinemap.users.entities.Log.AuditEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IAuditRepository extends MongoRepository<AuditEntity, ObjectId> {
}
