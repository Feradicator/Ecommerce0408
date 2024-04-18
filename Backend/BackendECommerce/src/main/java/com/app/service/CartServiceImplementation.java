package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.ProductException;
import com.app.model.Cart;
import com.app.model.User;
import com.app.repository.CartRepository;
import com.app.request.AddItemRequest;

@Service
public class CartServiceImplementation implements CartService{
	@Autowired
    private CartRepository cartRepository;
	@Autowired
    private CartItemService cartItemService;
	@Autowired
	private ProductService productService;
	@Override
	public Cart createCart(User user) {
		Cart cart=new Cart();
		cart.setUser(user);
		return cartRepository.save(cart);
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
