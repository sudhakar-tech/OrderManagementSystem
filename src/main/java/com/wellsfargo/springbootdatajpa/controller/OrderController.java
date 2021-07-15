package com.wellsfargo.springbootdatajpa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	public ResponseEntity<Order> addOrder(@RequestBody Order order, HttpServletRequest request,@RequestHeader HttpHeaders headers) {
		//String userName = (String) request.getSession().getAttribute("user");
		String userName = null;
		if (headers.containsKey("username"))
			userName = headers.get("username").get(0);
		User user = userServiceImpl.findByUserName(userName); 
		
		if (user != null) {
			order.setUser(user);
			order = orderServiceImpl.addOrder(order);
			if (order == null) {
				throw new RecordNotFoundException("order is not creted : " + order);
			}
			return new ResponseEntity<>(order, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("order is not creted : " + order);
		}
	}

	@PutMapping("/updateOrder/{orderId}")
	public ResponseEntity<Order> updateOrder(@PathVariable("orderId") int orderId, @RequestBody Order orderRequest, HttpServletRequest request,@RequestHeader HttpHeaders headers) {
		String userName = null;
		if (headers.containsKey("username"))
			userName = headers.get("username").get(0);
		User user = userServiceImpl.findByUserName(userName);
		if (user != null) {
			orderRequest.setUser(user);
			orderRequest.setOid(orderId);
		}
		Order oldOrders = orderServiceImpl.findByOrderId(orderRequest.getOid());
			if (oldOrders.getOid() == 0) {
				throw new RecordNotFoundException("Invalid order id : " + orderRequest.getOid());
			} else {
				oldOrders = orderServiceImpl.updateOrder(orderRequest, oldOrders);
			}
		return new ResponseEntity<>(oldOrders, HttpStatus.OK);
	}

	@GetMapping("/getOrdersById/{id}")
	public ResponseEntity<Order> getOrdersById(@PathVariable("id") int orderId, HttpServletRequest request, @RequestHeader HttpHeaders headers) {
		//String userName = (String) request.getSession().getAttribute("user");
		String userName = null;
		if (headers.containsKey("username"))
			userName = headers.get("username").get(0);
		User user = userServiceImpl.findByUserName(userName);
		Order orders = orderServiceImpl.findByOrderId(orderId);
		if (user.getUserName() == null) {
			throw new RecordNotFoundException("Invalid userName:" + userName);
		} else {
			if (orders.getOid() == 0) {
				throw new RecordNotFoundException("Invalid order id : " + orderId);
			}
		}
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
	
	@GetMapping("/getAllOrders")
	public ResponseEntity<List<Order>> findOrderByUser(HttpServletRequest request, @RequestHeader HttpHeaders headers) {
		//String userName = (String) request.getSession(false).getAttribute("user");
		String userName = null;
		if (headers.containsKey("username"))
			userName = headers.get("username").get(0);
						
		List<Order> order = null;
		User  user = userServiceImpl.findByUserName(userName);
		if (user.getUserName() == null) {
			throw new RecordNotFoundException("Invalid userName:" + userName);
		} else {
			order =  orderServiceImpl.findOrderByUser(user);
		}
		return new ResponseEntity<>(order,HttpStatus.OK);
	}

	@GetMapping("/getOrders")
	public ResponseEntity<List<Order>> getOrder() {
		List<Order> order = orderServiceImpl.getOrder();
		return new ResponseEntity<>(order, HttpStatus.OK);

	}


	@DeleteMapping("/delete/{orderId}")
	public ResponseEntity<Order> deleteOrder(@PathVariable("orderId") int orderId,@RequestHeader HttpHeaders headers) throws Exception {
		String userName = null;
		if (headers.containsKey("username"))
			userName = headers.get("username").get(0);
		User user = userServiceImpl.findByUserName(userName);
		Order orders = orderServiceImpl.findByOrderId(orderId);
		if (orders.getOid() == 0) {
			throw new RecordNotFoundException("Invalid order id : " + orderId);
		} else {
			orderServiceImpl.deleteOrder(orderId);
		}
		return new ResponseEntity<>(HttpStatus.OK);
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
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("Invalid order id : " + orderId);
		}
}


}
