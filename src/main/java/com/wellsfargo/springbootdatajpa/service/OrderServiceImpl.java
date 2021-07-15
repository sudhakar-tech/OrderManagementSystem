package com.wellsfargo.springbootdatajpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.springbootdatajpa.model.Order;
import com.wellsfargo.springbootdatajpa.model.User;
import com.wellsfargo.springbootdatajpa.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Order addOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public List<Order> getOrder() { 
		return orderRepository.findAll();
	}
	
	public List<Order> findOrderByUser(User user){
		return orderRepository.getOrderByUser(user.getUid());
	}
	 
	@Override
	public Order findByOrderId(int orderId) {
		return orderRepository.findById(orderId).get();
	}
	
	Order deletedOrder;
	@Override
    public Order updateOrder(Order newOrder,Order oldOrder){
		oldOrder.setOrderName(newOrder.getOrderName());
		oldOrder.setPrice(newOrder.getPrice());
        oldOrder.setQty(newOrder.getQty());
        //oldOrder.setUser(newOrder.getUser());
        return orderRepository.save(oldOrder);
    }
	
	@Override
	public Order deleteOrder(int orderId) throws Exception {
		try {
			deletedOrder = orderRepository.findById(orderId).orElse(null);
			if (deletedOrder == null) {
				throw new Exception("order id is not available");
			} else {
				orderRepository.deleteOrderById(orderId);
			}
		} catch (Exception ex) {
			throw ex;
		}
		return deletedOrder;
	}
}

