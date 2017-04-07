package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="size")
public class Size extends AbstractEntity{
	@Column(name="min")
	private int min;
	
	@Column(name="max")
	private int max;
	
	@OneToMany(mappedBy="size")
	private List<Item> items = new ArrayList<>();

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}