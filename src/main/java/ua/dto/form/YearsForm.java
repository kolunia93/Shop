package ua.dto.form;

import java.util.ArrayList;
import java.util.List;

import ua.entity.Item;

public class YearsForm {
	
	private int id;
	private String minYears;
	private String maxYears;
	private List<Item> items = new ArrayList<>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getMinYears() {
		return minYears;
	}
	public void setMinYears(String minYears) {
		this.minYears = minYears;
	}
	public String getMaxYears() {
		return maxYears;
	}
	public void setMaxYears(String maxYears) {
		this.maxYears = maxYears;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}	

}
