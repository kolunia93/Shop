package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>,JpaSpecificationExecutor<Order>{

	@Query("SELECT DISTINCT u FROM Order u LEFT JOIN FETCH u.userOrder JOIN FETCH u.items i LEFT JOIN FETCH i.name WHERE u.order=false")
	List<Order> findAllandList();
	
	@Query("SELECT DISTINCT u FROM Order u LEFT JOIN FETCH u.userOrder JOIN FETCH u.items i LEFT JOIN FETCH i.name WHERE u.order=true")
	List<Order> findAllandListOrd();
	
	@Query("SELECT DISTINCT u FROM Order u LEFT JOIN FETCH u.userOrder JOIN FETCH u.items i LEFT JOIN FETCH i.name ")
	List<Order> findAllwithFetch();


}
