package com.dinemap.dinemap.users.entities.Log;

import com.dinemap.dinemap.users.entities.Base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@Document(collection = "audit_logs")
public class AuditEntity extends BaseEntity {
    private String action;
    private String username;
    private String email;
    private Date timestamp;
    private String details;

    public AuditEntity(String action, String username, String details){
        this.action = action;
        this.username = username;
        this.timestamp = new Date();
        this.details = details;
    }

}
