package ua.editor;

import java.beans.PropertyEditorSupport;
import ua.entity.Category;
import ua.service.CategoryService;

public class CategoryEditor extends PropertyEditorSupport{

	private final CategoryService service;

	public CategoryEditor(CategoryService service) {
		this.service = service;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Category cs =  service.findOne(Integer.valueOf(text));
		setValue(cs);
	}
}