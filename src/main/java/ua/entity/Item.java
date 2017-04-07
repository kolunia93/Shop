package ua.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="items")
public class Item extends AbstractEntity {
	
	@OneToMany(mappedBy="item" , fetch=FetchType.LAZY)
	private List<Comment> comments = new ArrayList<>();
	
	@Column(name="price")
	private BigDecimal price;
	
	@Column(name = "version", nullable = true)
	private Integer version;

	@Column(name="remain")
	private int remain;
	
	@Enumerated
	private Season season;
	
	@ManyToMany
	private List<User> users = new ArrayList<>();
	
	@ManyToMany(mappedBy="items" ) 
	private List<Order> ordersItem = new ArrayList<>();
	
	@Enumerated
	private Gender gender;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category")
	private Category category;
	
	@ManyToOne(fetch=FetchType.LAZY )
	@JoinColumn(name="name")
	private Name name;
	
	@ManyToOne(fetch=FetchType.LAZY )
	@JoinColumn(name="descript")
	private Descript descript;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vender")
	private Vender vender;

	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="years")
	private Years years;

	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="size")
	private Size size;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	

	public Vender getVender() {
		return vender;
	}

	public void setVender(Vender vender) {
		this.vender = vender;
	}

	public Years getYears() {
		return years;
	}

	public void setYears(Years years) {
		this.years = years;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public int getRemain() {
		return remain;
	}

	public void setRemain(int remain) {
		this.remain = remain;
	}

	public Descript getDescript() {
		return descript;
	}

	public void setDescript(Descript descript) {
		this.descript = descript;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void deleteComment(Comment comment) {
		this.comments.remove(comment);
		
	}

	public List<Order> getOrdersItem() {
		return ordersItem;
	}

	public void setOrdersItem(List<Order> ordersItem) {
		this.ordersItem = ordersItem;
	}

	public void setRemainLow() {
		if(remain>0)
		this.remain--;
		
	}
	
}
