package ua.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_table")
public class Order extends AbstractEntity{
	
	@Column(name="bol")
	boolean order;
	
	@Column(name="date")
	private LocalDateTime date;
	
	@ManyToOne(fetch=FetchType.LAZY )
	@JoinColumn(name="userOrder_id")
	private User userOrder;

	@ManyToMany
	private List<Item>items=new ArrayList<>();

	public boolean isOrder() {
		return order;
	}

	public void setOrder(boolean order) {
		this.order = order;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public User getUserOrder() {
		return userOrder;
	}

	public void setUserOrder(User userOrder) {
		this.userOrder = userOrder;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items.addAll(items);
	}

	public Order() {
		
		this.date = LocalDateTime.now();
		this.order=false;
	}

	public void setItems(Item i) {
		this.items.add(i);
		
	}	
}
