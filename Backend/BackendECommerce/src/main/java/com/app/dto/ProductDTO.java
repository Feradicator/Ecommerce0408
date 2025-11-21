package com.app.dto;

import java.util.Set;

import lombok.Data;
@Data
public class ProductDTO {
private Long id;
private String title;
private String description;
private int price;
private int discountedPrice;
private int discountPersent;
private int quantity;
private String brand;
private String color;
private String imageUrl;
private int numRatings;
private CategoryDTO category;
private Set<SizeDTO> sizes;
}

