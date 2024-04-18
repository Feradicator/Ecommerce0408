package com.app.service;

import java.util.List;

import org.springframework.data.domain.Page;


import com.app.exception.ProductException;
import com.app.model.Product;
import com.app.request.CreateProductRequest;

public interface ProductService {
    public Product createProduct(CreateProductRequest req);
    public String deleteProduct(Long productid) throws ProductException;
    public Product updateProduct(Long productid, Product product1) throws ProductException;
    public Product findProductById(Long id) throws ProductException;
    public List<Product> findProductByCategory(String category);
    public Page<Product>getAllProduct(String category,List<String>colors, List<String>sizes, Integer minPrice, Integer maxPrice,
    Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);

}