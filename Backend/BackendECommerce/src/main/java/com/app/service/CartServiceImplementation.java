package com.app.service;

import org.springframework.stereotype.Service;

import com.app.exception.ProductException;
import com.app.model.Cart;
import com.app.model.User;
import com.app.repository.CartRepository;
import com.app.request.AddItemRequest;

@Service
public class CartServiceImplementation implements CartService{
    private CartRepository cartRepository;
    private
	@Override
	public Cart createCart(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart findUserCart(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
