package com.wellsfargo.springbootdatajpa.service;

import java.util.List;

import com.wellsfargo.springbootdatajpa.model.Order;
import com.wellsfargo.springbootdatajpa.model.User;

public interface OrderService {
	
	public Order addOrder(Order order);
	public Order updateOrder(Order order);
	public List<Order> getOrder();
	public List<Order> findOrderByUser(User user);
	public Order findByOrderId(int orderId);
	public Order deleteOrder(int orderId) throws Exception;

}
