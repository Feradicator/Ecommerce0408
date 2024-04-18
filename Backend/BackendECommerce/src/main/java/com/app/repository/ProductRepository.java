package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query("SELECT p FROM Product p " + // Added space after "p"
		    "WHERE (p.category.name = :category OR :category = ' ') " + // Added spaces around "="
		    "AND ((:minPrice IS NULL AND :maxPrice IS NULL) OR (p.discountedPrice BETWEEN :minPrice AND :maxPrice)) " + // Added spaces around "=" and "BETWEEN"
		    "AND (:minDiscount IS NULL OR p.discountPersent >= :minDiscount) " + // Added spaces around "=" and ">="
		    "ORDER BY " +
		    "CASE WHEN :sort = 'price_low' THEN p.discountedPrice END ASC, " + // Added spaces around ":sort" and "THEN"
		    "CASE WHEN :sort = 'price_high' THEN p.discountedPrice END DESC" ) // Added spaces around ":sort" and "THEN"


    public List<Product> filterProducts(@Param("category") String category,
    @Param("minPrice") Integer minPrice,
    @Param("minDiscount") Integer minDiscount,
    @Param("maxPrice") Integer maxPrice,
    @Param("sort") String sort);

}
