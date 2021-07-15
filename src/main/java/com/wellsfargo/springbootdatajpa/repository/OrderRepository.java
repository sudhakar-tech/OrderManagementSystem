package com.wellsfargo.springbootdatajpa.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.wellsfargo.springbootdatajpa.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	@Query(value = "SELECT * FROM ordermgmt.orders o where o.user_id = :uid", nativeQuery = true)
	List<Order> getOrderByUser(@Param("uid") int uid);
	
	@Modifying
	@Transactional
	@Query(value = "delete FROM ordermgmt.orders where oid = :oid", nativeQuery = true)
	void deleteOrderById(@Param("oid") int oid);
}
