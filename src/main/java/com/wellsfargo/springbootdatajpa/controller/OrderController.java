package com.wellsfargo.springbootdatajpa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.springbootdatajpa.exceptions.RecordNotFoundException;
import com.wellsfargo.springbootdatajpa.model.Order;
import com.wellsfargo.springbootdatajpa.model.User;
import com.wellsfargo.springbootdatajpa.service.OrderServiceImpl;
import com.wellsfargo.springbootdatajpa.service.UserServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

	@Autowired
	private OrderServiceImpl orderServiceImpl;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping("/addOrder")
	public ResponseEntity<Order> addOrder(@RequestBody Order order, HttpServletRequest request) {
		String userName = (String) request.getSession().getAttribute("user");
		User user = userServiceImpl.findByUserName(userName);
		if (user != null) {
			order.setUser(user);
			order = orderServiceImpl.addOrder(order);
			if (order == null) {
				throw new RecordNotFoundException("order is not creted : " + order);
			}
			return new ResponseEntity<Order>(order, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("order is not creted : " + order);
		}

	}

	@PutMapping("/updateOrder/{orderId}")
	public ResponseEntity<Order> updateOrder(@RequestBody Order order,HttpServletRequest request) {
		String userName = (String) request.getSession().getAttribute("user");
		User user = userServiceImpl.findByUserName(userName);
		if(user != null) {
			order.assignUser(user);
			order = orderServiceImpl.findByOrderId(order.getOid());
			if (order.getOid() == 0) {
				throw new RecordNotFoundException("Invalid order id : " + order.getOid());
			} else {
				order = orderServiceImpl.updateOrder(order);
			}
		}
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	
		
	}

	@GetMapping("/getAllOrders")
	public List<Order> findOrderByUser(HttpServletRequest request) {
		String userName = (String) request.getSession().getAttribute("user");
		if (userName == null) {
			throw new RecordNotFoundException("Invalid userName:" + userName);
		} else {
			User user = userServiceImpl.findByUserName(userName);
			return orderServiceImpl.findOrderByUser(user);
		}
	}

	@GetMapping("/getOrders")
	public ResponseEntity<List<Order>> getOrder() {
		List<Order> orders = orderServiceImpl.getOrder();
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);

	}

	@GetMapping("/getOrdersById/{id}")
	public ResponseEntity<Order> getOrdersById(@PathVariable("id") int orderId) {
		Order orders = orderServiceImpl.findByOrderId(orderId);
		if (orders == null) {
			throw new RecordNotFoundException("Invalid order id : " + orderId);
		}
		return new ResponseEntity<Order>(orders, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{orderId}")
	public ResponseEntity<Order> deleteOrder(@PathVariable("orderId") int orderId) throws Exception {
		Order isOrderExist = orderServiceImpl.findByOrderId(orderId);
		orderServiceImpl.deleteOrder(orderId);
		if (isOrderExist == null) {
			throw new RecordNotFoundException("Invalid order id : " + orderId);
		} else {
			return new ResponseEntity<Order>(HttpStatus.OK);
		}

	}
	
	@PutMapping("/{orderId}/user/{userId}")
	public ResponseEntity<Order> assignUserToOrder(@PathVariable int orderId, @PathVariable int userId) {
		Order orders = orderServiceImpl.findByOrderId(orderId);
		User user = userServiceImpl.findByuserId(userId);
		if (orders != null) {
			orders.assignUser(user);
			orders = orderServiceImpl.addOrder(orders);
			if (orders.getOid() == 0) {
				throw new RecordNotFoundException("Invalid order id : " + orderId);
			}
			return new ResponseEntity<Order>(orders, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("Invalid order id : " + orderId);
		}
}


}
