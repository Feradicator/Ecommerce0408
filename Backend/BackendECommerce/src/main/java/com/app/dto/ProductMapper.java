package com.app.dto;

import java.util.stream.Collectors;

import com.app.model.Product;
import com.app.model.Size;

public class ProductMapper {


public static ProductDTO toDTO(Product p) {
    ProductDTO dto = new ProductDTO();
    dto.setId(p.getId());
    dto.setTitle(p.getTitle());
    dto.setDescription(p.getDescription());
    dto.setPrice(p.getPrice());
    dto.setDiscountedPrice(p.getDiscountedPrice());
    dto.setDiscountPersent(p.getDiscountPersent());
    dto.setQuantity(p.getQuantity());
    dto.setBrand(p.getBrand());
    dto.setColor(p.getColor());
    dto.setImageUrl(p.getImageUrl());
    dto.setNumRatings(p.getNumRatings());

    // Convert Category -> CategoryDTO
    if (p.getCategory() != null) {
        CategoryDTO cat = new CategoryDTO();
        cat.setId(p.getCategory().getId());
        cat.setName(p.getCategory().getName());
        dto.setCategory(cat);
    }

    // Convert sizes list
    if (p.getSizes() != null) {
        dto.setSizes(
            p.getSizes()
                .stream()
                .map(Size::getName)
                .collect(Collectors.toSet())
        );
    }

    return dto;
}


}
