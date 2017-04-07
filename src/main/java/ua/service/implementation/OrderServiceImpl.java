package ua.service.implementation;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Item;
import ua.entity.Order;
import ua.entity.User;
import ua.repository.ItemsRepository;
import ua.repository.OrderRepository;
import ua.repository.UserRepository;
import ua.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ItemsRepository itemRepository;

	@Transactional
	@Override
	public void save(Principal principal) {
		Order order=new Order();
		User user=userRepository.findByUsername(principal.getName());
		Iterator <Item> items=user.getItems().iterator();
		while(items.hasNext())	
		items.next().setRemainLow();
		
		order.setItems(user.getItems());
		order.setUserOrder(user);
		orderRepository.save(order);
		
		BigDecimal sum =new BigDecimal(0);

		String mail=user.getEmail();
		StringBuilder buffer = new StringBuilder();
		String content="Покупку оформлено, найблищим часом з вами звяжиться менеджер";
		buffer.append("Доброго дня, вас вітає інтернет магазин *** \n");
		buffer.append("Ви замовили : \n");
		for(Item i:user.getItems()){
		buffer.append(i.getName().getName());
		buffer.append(", ціною ");
		buffer.append(i.getPrice().toString());
		buffer.append("\n");
		sum=sum.add(i.getPrice());
		}
		buffer.append("Сумма до сплати:\n");
		buffer.append(sum.toString());
		String mailBody=buffer.toString();
		System.out.println(mailBody);
		sendMail(content,mail,mailBody);
		Iterator <Item> iter= user.getItems().iterator(); 
		while(iter.hasNext()){ 
		if(iter.next() != null){ 
		iter.remove(); 
		user.setCount(user.getCount()-1);
		
		} 
		}
	}
	
	@Override 
	public void sendMail(String content, String email, String mailBody) { 
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
	try { 
	MimeMessage message = new MimeMessage(session); 
	message.setFrom(new InternetAddress("повине бути емейл")); 
	message.addRecipient(Message.RecipientType.TO, new InternetAddress( 
	email)); 
	message.setSubject(content, "UTF-8"); 
	message.setText(mailBody); 
	Transport.send(message); 
	} catch (MessagingException е) { 
	е.printStackTrace(); 
	} 
	}

	@Override
	public List<Order> findAll() {
		
		return orderRepository.findAllandList();
	}

	@Override
	public Order findById(int id) {
		return orderRepository.findOne(id);
	}

	@Override
	public void save(Order order) {
		orderRepository.save(order);		
	}

	@Override
	public List<Order> findFinish() {		
		return orderRepository.findAllandListOrd();
	}

	@Override 
	@Transactional 
	public void deletebuy(int id){ 
		Item item = itemRepository.findOne(id);		
		List <Order> orders=orderRepository.findAllwithFetch();
		for(Order ord:orders){
		Iterator <Item> iter= ord.getItems().iterator();
		while(iter.hasNext()){ 
		if(iter.next().equals(item)){ 
		iter.remove(); 
		} 
		} 
		}
	}

}
