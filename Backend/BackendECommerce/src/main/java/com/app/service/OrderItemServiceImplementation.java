package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.OrderItem;
import com.app.repository.OrderItemRepository;
@Service
@Transactional
public class OrderItemServiceImplementation implements OrderItemService {
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Override
	public OrderItem createOrderItem(OrderItem orderItem) {
		return orderItemRepository.save(orderItem);
	}

}
