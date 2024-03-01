package com.example.backendprojectmodule_productservice.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private String username;
    private String email;
    private List<Role> roles;
}
