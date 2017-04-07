package ua.service;


import java.security.Principal;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.filter.ItemFilter;
import ua.dto.form.UserForm;
import ua.entity.User;

public interface UserService {

	void save(UserForm user);

	Page <User>findAll(ItemFilter filter, Pageable pageable);

	User findOne(String email);


	List<User> findAll();

	User finaByName(String name);

	void saveItem(Principal principal, int id);

	List <User> findBuy(Pageable pageable);

	User findUserBuy(Principal principal);

	void deletebuy(int id, Principal principal);

	User finaByFone(String fone);

	List<User> findByItem(int id);

	User findOne(Integer valueOf);

	void deletebuy(int id);

	List<User> findAllfetchItem();

	void cheackMail(String email) throws AddressException, MessagingException;





}