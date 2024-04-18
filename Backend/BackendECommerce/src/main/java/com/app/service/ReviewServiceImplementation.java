package com.app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.exception.ProductException;
import com.app.model.Review;
import com.app.model.User;
import com.app.model.Product;
import com.app.repository.ReviewRepository;
import com.app.request.ReviewRequest;
import com.app.repository.ProductRepository;
public class ReviewServiceImplementation implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
	@Override
	public Review createReview(ReviewRequest req, User user) throws ProductException {
	Product product=productService.findProductById(req.getProductId());
    Review review=new Review(); 
    review.setUser(user);
    review.setProduct(product);
    review.setReview(req.getReview());
    review.setCreatedAt(LocalDateTime.now());
    return reviewRepository.save(review);

	}

	@Override
	public List<Review> getAllReview(Long productId) {
		// TODO Auto-generated method stub
		return reviewRepository.getAllProductsReview(productId);
	}

}
