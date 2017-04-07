package ua.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="comment")
public class Comment extends AbstractEntity{
	@Column(name="comment")
	String comment;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="comments")
	private Item item;

	@Column(name="date")
	private LocalDateTime date;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="coments")
	private User user;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Comment() {
		this.date = LocalDateTime.now();
	}

	public LocalDateTime getDate() {
		return date;
	}
	
}
