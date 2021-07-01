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
		return (List<Order>) orderRepository.getOrderByUser(user.getUid());
	}
	 
	@Override
	public Order findByOrderId(int orderId) {
		return orderRepository.findById(orderId).get();
	}

	@Override
    public Order updateOrder(Order order){
        Order oldOrder = orderRepository.findById(order.getOid()).orElse(null);
        oldOrder.setOrderName(order.getOrderName());
        oldOrder.setQty(order.getQty());
        oldOrder.setPrice(order.getPrice());
        oldOrder.setUser(order.getUser());
        return orderRepository.save(oldOrder);
    }
	
	@Override
	public Order deleteOrder(int orderId) throws Exception {
		Order deletedOrder = null;
		try {
			deletedOrder = orderRepository.findById(orderId).orElse(null);
			if (deletedOrder == null) {
				throw new Exception("order id is not available");
			} else {
				orderRepository.deleteById(orderId);
			}
		} catch (Exception ex) {
			throw ex;
		}
		return deletedOrder;
	}
}

