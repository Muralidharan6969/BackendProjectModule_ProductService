package com.example.backendprojectmodule_productservice.Commons;

import com.example.backendprojectmodule_productservice.DTOs.UserDTO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationCommons {
    private RestTemplate restTemplate;

    @Autowired
    public AuthenticationCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDTO validateToken(String token){

       ResponseEntity<UserDTO> validationResponse = restTemplate.postForEntity(
               "http://localhost:8181/users/validate/" + token,
                null,
                UserDTO.class
       );

       if (validationResponse.getBody() == null){
           return null;
       }

       return validationResponse.getBody();
    }
}
