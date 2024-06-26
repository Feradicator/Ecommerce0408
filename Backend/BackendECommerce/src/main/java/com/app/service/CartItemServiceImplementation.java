package com.app.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.CartItemException;
import com.app.exception.UserException;
import com.app.model.Cart;
import com.app.model.User;
import com.app.model.CartItem;
import com.app.model.Product;
import com.app.repository.CartItemRepository;

@Service
@Transactional
public class CartItemServiceImplementation implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserService userService;
    
	@Override
	public CartItem createCartItem(CartItem cartItem) {
		cartItem.setQuantity(1);
		cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
		cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());
		CartItem createdCartItem=cartItemRepository.save(cartItem);
		return createdCartItem;

		
	}

	@Override
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
		CartItem item=findCartItemById(id);
		
		User user=userService.findUserById(item.getUserId());
		if(user.getId().equals(userId)) {

		item.setQuantity(cartItem.getQuantity());
		item.setPrice(item.getQuantity()*item.getProduct().getPrice());
		item.setDiscountedPrice(item.getProduct().getDiscountedPrice()*item.getQuantity());
		}
		return cartItemRepository.save(item);

	}

	@Override
	public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
		CartItem cartItem=cartItemRepository.isCartItemExist(cart, product, size, userId);
		return cartItem;
	}

	@Override
	public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
		CartItem cartItem=findCartItemById(cartItemId);
		User user=userService.findUserById(cartItem.getUserId());
		User reqUser=userService.findUserById(userId);
		if(user.getId().equals(reqUser.getId()))
		{
			cartItemRepository.deleteById(cartItemId);
		}
		else
		throw new UserException("You cant remove another user item");
		
	}

	@Override
	public CartItem findCartItemById(Long cartItemId) throws CartItemException {
		Optional<CartItem>opt=cartItemRepository.findById(cartItemId);
		if(opt.isPresent())
		return opt.get();
		else
		throw new CartItemException("cartItem not found with id "+cartItemId);
	}

}
