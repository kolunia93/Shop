package ua.service;

import java.security.Principal;
import java.util.List;

import ua.entity.Order;

public interface OrderService {

	void save(Principal principal);

	void sendMail(String content, String email, String mailBody);

	List< Order> findAll();

	Order findById(int id);

	void save(Order order);

	List< Order> findFinish();

	void deletebuy(int id);




}
