package com.dinemap.dinemap.users.entities;

import com.dinemap.dinemap.users.entities.Base.BaseEntity;
import com.dinemap.dinemap.users.entities.Enum.UsersRoles;
import com.dinemap.dinemap.users.entities.Enum.UsersStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class UsersEntity extends BaseEntity {
    private String fullname;
    @NotEmpty(message = "Este campo es necesario")
    private String username;
    private String email;
    @NotEmpty(message = "Este campo es necesario")
    @Size(min = 16, message = "Su contraseña debe tener 16 digitos")
    private String password;
    private String phoneNumber;
    private String address;
    private String province;
    private String district;
    private UsersRoles role = UsersRoles.CLIENT;
    private UsersStatus status = UsersStatus.ENABLED;
}
