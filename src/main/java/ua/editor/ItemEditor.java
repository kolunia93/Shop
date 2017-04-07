package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Item;
import ua.service.ItemService;

public class ItemEditor extends PropertyEditorSupport{

	private final ItemService service;

	public ItemEditor(ItemService service) {
		this.service = service;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Item cs =  service.findOneItem(Integer.valueOf(text));
		setValue(cs);
	}
}