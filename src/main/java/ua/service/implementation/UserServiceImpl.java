package ua.service.implementation;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.dto.filter.ItemFilter;
import ua.dto.form.UserForm;
import ua.entity.Item;
import ua.entity.Role;
import ua.entity.User;
import ua.repository.ItemsRepository;
import ua.repository.UserRepository;
import ua.service.UserService;

@Service("userDetailsService")
public class UserServiceImpl implements UserDetailsService, UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ItemsRepository itemRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}

	@Override
	public void save(UserForm userForm) {
		User user=new User();
		user.setPassword(encoder.encode(userForm.getPassword()));
		user.setRole(Role.ROLE_USER);
		user.setEmail(userForm.getEmail());
		user.setFone(userForm.getFone());
		user.setUsername(userForm.getUsername());
		userRepository.save(user);
	}
	
	@PostConstruct
	public void admin(){
		User user = userRepository.findByUsername("admin");
		if(user==null){
			user = new User();
			user.setEmail("");
			user.setPassword(encoder.encode("admin"));
			user.setRole(Role.ROLE_ADMIN);
			user.setUsername("admin");
			userRepository.save(user);
		}
	}

	@Override
	public Page<User> findAll(ItemFilter filter, Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public User findOne(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> findAll() {
		
		return userRepository.findAll();
	}

	@Override
	public User finaByName(String name) {
		return userRepository.findByUsername(name);
	}

	@Override
	@Transactional
	public void saveItem(Principal principal, int id) {
		User user=userRepository.findByUsername(principal.getName());
		Item item=itemRepository.findOne(id);
		user.setItems(item);
		userRepository.save(user);
	}

	@Override
	public List<User> findBuy(Pageable pageable) {
		return userRepository.findAllBuy(pageable);
	}

	@Override
	public User findUserBuy(Principal principal) {
		if(principal!=null){
		String name=principal.getName();		
		return userRepository.findAllitems(name);}
		return new User();
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	public ItemsRepository getItemRepository() {
		return itemRepository;
	}


	public void setItemRepository(ItemsRepository itemRepository) {
		this.itemRepository = itemRepository;
	}


	public BCryptPasswordEncoder getEncoder() {
		return encoder;
	}


	public void setEncoder(BCryptPasswordEncoder encoder) {
		this.encoder = encoder;
	}

	@Override 
	@Transactional 
	public void deletebuy(int id, Principal principal){ 
	User user = userRepository.findByUsername(principal.getName()); 
	Item item = itemRepository.findOne(id);
	Iterator <Item> iter= user.getItems().iterator(); 
	while(iter.hasNext()){ 
	if(iter.next().equals(item)){ 
	iter.remove(); 
	user.setCount(user.getCount()-1);
	break;
	} 
	} 
	}

	@Override
	public User finaByFone(String fone) {
	
		return userRepository.findByFone(fone);
	}


	@Override
	public List<User> findByItem(int id) {
		return userRepository.findByItems(id);
	}

	@Override
	public User findOne(Integer valueOf) {
		return userRepository.findOne(valueOf);
	}

	@Override 
	@Transactional 
	public void deletebuy(int id){ 
		Item item = itemRepository.findOne(id);
		List<User> users = userRepository.findByItems(id);
		for(User user:users){
		Iterator <Item> iter= user.getItems().iterator(); 
		while(iter.hasNext()){ 
		if(iter.next().equals(item)){
		iter.remove(); 
		user.setCount(user.getCount()-1);
		}
			} 
		}
	}


	@Override
	public List<User> findAllfetchItem() {
		
		return userRepository.findAllFetch();
	}


	@Override
	public void cheackMail(String email) throws AddressException, MessagingException {
		String content="Реєстрація завершена";
		String mailBody="Ви успішно зарєструвались на сайті";
		Properties properties= System.getProperties(); 
		properties.setProperty("mail.smtp.auth", "true"); 
		properties.setProperty("mail.smtp.port", "465"); 
		properties.setProperty("mail.smtp.host", "smtp.gmail.com"); 
		properties.setProperty("mail.smtp.socketFactory.port", "465"); 
		properties.setProperty("mail.smtp.socketFactory.class", 
		"javax.net.ssl.SSLSocketFactory"); 
		Session session = Session.getDefaultInstance(properties, 
		new Authenticator() { 
		protected PasswordAuthentication getPasswordAuthentication() { 
		
		return new PasswordAuthentication("тут повинен бути емейл", "тут повинен бути пароль"); 
		} 
		}); 
		
		MimeMessage message = new MimeMessage(session); 
		message.setFrom(new InternetAddress("тут повине бути емйл")); 
		message.addRecipient(Message.RecipientType.TO, new InternetAddress( 
		email)); 
		message.setSubject(content, "UTF-8"); 
		message.setText(mailBody); 
		Transport.send(message); 
		
		}
	
	} 
