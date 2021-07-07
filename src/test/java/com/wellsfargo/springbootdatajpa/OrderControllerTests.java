package com.wellsfargo.springbootdatajpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import com.wellsfargo.springbootdatajpa.controller.OrderController;
import com.wellsfargo.springbootdatajpa.model.Order;
import com.wellsfargo.springbootdatajpa.model.User;
import com.wellsfargo.springbootdatajpa.repository.OrderRepository;
import com.wellsfargo.springbootdatajpa.service.CustomUserDetailsServiceImpl;
import com.wellsfargo.springbootdatajpa.service.EmailSenderServiceImpl;
import com.wellsfargo.springbootdatajpa.service.OrderServiceImpl;
import com.wellsfargo.springbootdatajpa.service.UserServiceImpl;
import com.wellsfargo.springbootdatajpa.util.JwtUtil;


@AutoConfigureMockMvc
@ContextConfiguration(classes = {OrderController.class})
@WebMvcTest
class OrderControllerTests {
		
	@MockBean
	private OrderRepository orderRepository;
	
	@MockBean
	private UserServiceImpl userServiceImpl;
	
	@MockBean
	private OrderServiceImpl orderServiceImpl;
	
	@MockBean
	private CustomUserDetailsServiceImpl customUserDetailsServiceImpl;
	
	@MockBean
	private JwtUtil jwtUtil;
	
	@MockBean
	private EmailSenderServiceImpl emailSenderServiceImpl;
	
	@Test
	void addOrderTest() {
		String userName = "hello123";
		User user = new User();
		user.setUserName(userName);
		Order order = new Order();
		order.setOrderName("order1");
		order.setQty(5);
		order.setPrice(100);
		order.setUser(user);
		Mockito.when(userServiceImpl.findByUserName(userName)).thenReturn(user);
		Mockito.when(orderServiceImpl.addOrder(order)).thenReturn(order);
		assertEquals(userName, user.getUserName());
		assertEquals(order, order);
	}

	@Test
	void deleteOrderTest() throws Exception {
		User user = new User();
		user.getUid();
		Order order = new Order();
		order.setOid(1);
		order.setOrderName("hello");
		order.setQty(2);
		order.setPrice(100);
		order.setUser(user);
		Mockito.when(orderServiceImpl.findByOrderId(order.getOid())).thenReturn(order);
		Mockito.when(orderServiceImpl.deleteOrder(order.getOid())).thenReturn(order);
		assertEquals(order.getOid(),1);
		
	}
	
	@Test
	void getOrdersByIdTest(){
		List<Order> orders = new ArrayList<Order>();
		String userName = "hello123";
		User user = new User();
		user.setUserName(userName);
		Mockito.when(userServiceImpl.findByUserName(userName)).thenReturn(user);
		Order order = new Order();
		order.setOid(1);
		order.setOrderName("hello");
		order.setQty(2);
		order.setPrice(100);
		order.setUser(user);
		orders.add(order);
		Mockito.when(orderServiceImpl.findByOrderId(order.getOid())).thenReturn(order);
		assertEquals(userName, user.getUserName());
		assertNotNull(order);
		
	}
	
	@Test
	void findOrderByUserTest() {
		String userName = "hello";
		User user = new User();
		user.setFirstName("hi");
		user.setLastName("hi123");
		user.setUserName(userName);
		user.setPassword("hello123");
		Mockito.when(userServiceImpl.findByUserName(userName)).thenReturn(user);
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(10, "qwe", 5, 100, user));
		Mockito.when(orderServiceImpl.findOrderByUser(user)).thenReturn(orders);
		assertEquals(userName, user.getUserName());
		assertEquals(orders, orders);
	}
}
