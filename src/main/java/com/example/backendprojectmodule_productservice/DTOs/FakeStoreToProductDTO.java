package com.example.backendprojectmodule_productservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreToProductDTO {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
}
