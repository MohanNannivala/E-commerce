package com.ooothla.productservice.dtos;


import lombok.Data;

@Data
public class GenericProductDto {
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}
