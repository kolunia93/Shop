package ua.dto.form;

import java.util.ArrayList;
import java.util.List;

import ua.entity.Item;

public class SizeForm {
	private int id;
	private String minSize;
	private String maxSize;
	private List<Item> items = new ArrayList<>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getMinSize() {
		return minSize;
	}
	public void setMinSize(String minSize) {
		this.minSize = minSize;
	}
	public String getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(String maxSize) {
		this.maxSize = maxSize;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}


}
