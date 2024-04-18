package com.app.service;

import com.app.request.AddItemRequest;
import com.app.exception.ProductException;
import com.app.model.Cart;
import com.app.model.User;
public interface CartService {
    public Cart createCart(User user);
    public String addCartItem(Long userId,AddItemRequest req) throws ProductException;
    public Cart findUserCart(Long userId);

}
