package ua.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="_user", indexes=@Index(columnList = "_name"))
public class User implements UserDetails{
	
	private static final long serialVersionUID = -8288001889276940237L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY  )
	private List<Comment> comments = new ArrayList<>();
	
	@OneToMany(mappedBy="userOrder" ,fetch=FetchType.LAZY)
	private List<Order> ordersUser = new ArrayList<>();
	
	@Column(name="_name")
	private String username;
	
	private String password;
	
	private String email;
	
	private String fone;
	@Enumerated
	private Role role;
	
	@Column(name="_count")
	private int count;
	
	@ManyToMany(fetch=FetchType.LAZY)
	private List<Item>items=new ArrayList<>();		
	
	public void setRole(Role role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority(role.name()));
		return list;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
		count=this.items.size();
	}
	
	public void setItems(Item items) {
		this.items.add(items);
		count=this.items.size();
		
	}

	public Role getRole() {
		return role;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Comment> getComents() {
		return comments;
	}

	public void setComents(List<Comment> coments) {
		this.comments = coments;
	}

	public void deleteComment(Comment comment) {
		this.comments.remove(comment);
		
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public List<Order> getOrders() {
		return ordersUser;
	}

	public void setOrders(List<Order> orders) {
		this.ordersUser = orders;
	}

	public void setCountMin() {
		this.count--;
		
	}
	
}