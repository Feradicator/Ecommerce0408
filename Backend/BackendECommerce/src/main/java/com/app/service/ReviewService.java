package com.app.service;

import com.app.request.ReviewRequest;
import com.app.model.User;
import com.app.model.Review;

import java.util.List;

import com.app.exception.ProductException;

public interface ReviewService {
    public Review createReview(ReviewRequest req,User user) throws ProductException;
    public List<Review>getAllReview(Long productId);

}
