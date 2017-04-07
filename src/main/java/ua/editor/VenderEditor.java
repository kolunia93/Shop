package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Vender;
import ua.service.VenderService;



public class VenderEditor extends PropertyEditorSupport{

	private final VenderService service;

	public VenderEditor(VenderService service) {
		this.service = service;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Vender vs =  service.findOne(Integer.valueOf(text));
		setValue(vs);
	}
}