package ua.editor;

import java.beans.PropertyEditorSupport;
import ua.entity.Descript;
import ua.service.DescriptService;

public class DescriptEditor extends PropertyEditorSupport {

	private final DescriptService service;

	public DescriptEditor(DescriptService service) {
		this.service = service;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Descript des = service.findOne(Integer.valueOf(text));
		setValue(des);
	}
}