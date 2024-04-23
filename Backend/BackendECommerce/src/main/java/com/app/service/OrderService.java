package com.app.service;

import java.util.List;
import com.app.model.User;
import com.app.model.Address;
import com.app.exception.OrderException;
import com.app.model.Order;

public interface OrderService {
    public Order createdOrder(User user,Address shippingAddress);
    public Order findOrderById(Long orderId) throws OrderException;
    public List<Order> usersOrderHistory(Long userld);
    public Order placedOrder(Long orderId) throws OrderException; 
    public Order confirmedOrder(Long orderId)throws OrderException;
    public Order shippedOrder(Long orderId) throws OrderException; 
    public Order deliveredOrder(Long orderId) throws OrderException;
    public Order cancledOrder(Long orderId) throws OrderException; 
    public List<Order>getAllOrders();
    public void deleteOrder(Long orderId) throws OrderException;


}
