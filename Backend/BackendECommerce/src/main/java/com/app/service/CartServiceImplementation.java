package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.ProductException;
import com.app.model.Cart;
import com.app.model.CartItem;
import com.app.model.Product;
import com.app.model.User;
import com.app.repository.CartRepository;
import com.app.request.AddItemRequest;

@Service
@Transactional
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
		Cart cart=cartRepository.findByUserId(userId);
		System.out.println(cart);
		 Product product=productService.findProductById(req.getProductId());
		 System.out.println(product);
		 CartItem isPresent=cartItemService.isCartItemExist(cart, product,req.getSize(), userId);
		 System.out.println(
			isPresent
		 );
		if(isPresent==null)
		{
			CartItem cartItem=new CartItem();
			cartItem.setProduct(product);
			cartItem.setCart(cart);
			cartItem.setQuantity(req.getQuantity());
			System.out.println(req.getQuantity());
			cartItem.setUserId(userId);
			System.out.println(cartItem);
			int price =req.getQuantity()*product.getDiscountedPrice();
			cartItem.setPrice(price);
			cartItem.setSize(req.getSize());
			System.out.println(cartItem);
			CartItem createdCartItem=cartItemService.createCartItem(cartItem);
			cart.getCartItems().add(createdCartItem);
		}

		return "Item added to cart Successfully";
	}

	@Override
	public Cart findUserCart(Long userId) {
		
		Cart cart=cartRepository.findByUserId(userId);
		int totalPrice=0;
		int totalDiscountedPrice=0;
		int totalItem=0;
		
		for(CartItem cartItem :cart.getCartItems()) {
		
		totalPrice=totalPrice+cartItem.getPrice();
		totalDiscountedPrice=totalDiscountedPrice+cartItem.getDiscountedPrice();
		totalItem=totalItem+cartItem.getQuantity();
		
		}
		cart.setTotalDiscountedPrice(totalDiscountedPrice);
		cart.setTotalItem(totalItem);
		cart.setTotalPrice(totalPrice);
		cart.setDiscounte(totalPrice-totalDiscountedPrice);
		return cartRepository.save(cart);

	}

}
