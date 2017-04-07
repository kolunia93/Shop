package ua.editor;

import java.beans.PropertyEditorSupport;
import ua.entity.Name;
import ua.service.NameService;

public class NameEditor extends PropertyEditorSupport {

	private final NameService service;

	public NameEditor(NameService service) {
		this.service = service;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Name ns =  service.findOne(Integer.valueOf(text));
		setValue(ns);
	}
}