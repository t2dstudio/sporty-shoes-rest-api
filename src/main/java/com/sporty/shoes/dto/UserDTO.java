package com.sporty.shoes.dto;

import javax.validation.constraints.Email;

import com.sporty.shoes.ann.ValueOfEnumUserRole;
import com.sporty.shoes.entity.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {
    @Email
    private String email;
    private String username;
    private String password;
    @ValueOfEnumUserRole(enumClass = UserRole.class)
    private String role;
    private boolean enabled;

}
