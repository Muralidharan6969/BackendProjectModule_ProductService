package com.example.backendprojectmodule_productservice.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class Product extends BaseModel implements Serializable {
    private String title;
    private double price;
    private String description;
    private String imageURL;
    @ManyToOne
    private Category category;
}
