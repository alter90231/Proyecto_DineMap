package com.dinemap.dinemap.restaurants.entities.Base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity {
    @Id
    private ObjectId _id;
    @CreatedBy
    private String createdBy;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedBy
    private String updateBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
