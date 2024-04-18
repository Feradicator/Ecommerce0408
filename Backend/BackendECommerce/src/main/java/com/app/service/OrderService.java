package com.app.service;

import java.util.List;
import com.app.model.User;
import com.app.model.Address;
import com.app.exception.OrderException;
import com.app.model.Order;

public interface OrderService {
    public Order createdOrder(User user,Address shippingAddress);
    public Order findOrderByld(Long orderld) throws OrderException;
    public List<Order> usersOrderHistory(Long userld);
    public Order placedOrder(Long orderld) throws OrderException; 
    public Order confirmedOrder(Long orderld)throws OrderException;
    public Order shippedOrder(Long orderld) throws OrderException; 
    public Order deliveredOrder(Long orderld) throws OrderException;
    public Order cancledOrder(Long orderld) throws OrderException; 
    public List<Order>getAllOrders();
    public void deleteOrder(Long orderld) throws OrderException;


}
