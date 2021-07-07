package com.wellsfargo.springbootdatajpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import com.wellsfargo.springbootdatajpa.model.Order;
import com.wellsfargo.springbootdatajpa.model.User;
import com.wellsfargo.springbootdatajpa.repository.OrderRepository;
import com.wellsfargo.springbootdatajpa.service.OrderServiceImpl;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {OrderServiceImpl.class})
@WebMvcTest
public class OrderServiceImplTest {
	
	@MockBean
	private OrderRepository orderRepository;
	
	@Test
	void addOrderTest() {
		User user = new User();
		user.setUid(10);
		Order order = new Order();
		order.setOid(1);
		order.setOrderName("orange");
		order.setQty(10);
		order.setPrice(1000);
		order.setUser(user);
		Mockito.when(orderRepository.save(order)).thenReturn(order);
		assertEquals(order, order);		
	}
	
	@Test
	void getOrderTest() {
		List<Order> orders = new ArrayList<Order>();
		User user = new User();
		user.setUid(10);
		Order order = new Order();
		order.setOid(1);
		order.setOrderName("orange");
		order.setQty(10);
		order.setPrice(1000);
		order.setUser(user);
		orders.add(order);
		Mockito.when(orderRepository.findAll()).thenReturn(orders);
		assertEquals(1, orders.size());	
		
	}

	@Test
	void findOrderByUserTest() {
		List<Order> orders = new ArrayList<Order>();
		User user = new User();
		user.setUid(10);
		Order order = new Order();
		order.setOid(1);
		order.setOrderName("orange");
		order.setQty(10);
		order.setPrice(1000);
		order.setUser(user);
		orders.add(order);
		Mockito.when(orderRepository.getOrderByUser(user.getUid())).thenReturn(orders);
		assertEquals(10, order.getUser().getUid());	
		
	}
	
	@Test
	void findByOrderIdTest() {
		List<Order> orders = new ArrayList<Order>();
		User user = new User();
		user.setUid(10);
		Order order = new Order();
		order.setOid(1);
		order.setOrderName("orange");
		order.setQty(10);
		order.setPrice(1000);
		order.setUser(user);
		orders.add(order);
		Mockito.when(orderRepository.getOrderByUser(order.getOid())).thenReturn(orders);
		assertEquals(1, orders.size());	
		
	}
	

}
