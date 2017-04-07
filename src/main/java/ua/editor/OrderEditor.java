package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Order;
import ua.service.OrderService;

public class OrderEditor extends PropertyEditorSupport{

	private final OrderService service;

	public OrderEditor(OrderService service) {
		this.service = service;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Order cs =  service.findById(Integer.valueOf(text));
		setValue(cs);
	}
}