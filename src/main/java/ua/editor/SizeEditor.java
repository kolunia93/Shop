package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.dto.form.SizeForm;
import ua.service.SizeService;

public class SizeEditor extends PropertyEditorSupport{

	private final SizeService service;

	public SizeEditor(SizeService service) {
		this.service = service;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		SizeForm ss =  service.findOne(Integer.valueOf(text));
		setValue(ss);
	}
}