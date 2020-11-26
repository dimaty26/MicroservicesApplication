package com.zmeevsky.administration.restservice.dto;

import com.zmeevsky.administration.restservice.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveUserResponse {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<Role> roles;
}
