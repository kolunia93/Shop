package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.dto.form.YearsForm;
import ua.service.YearsService;

public class YearsEditor extends PropertyEditorSupport{

	private final YearsService service;

	public YearsEditor(YearsService service) {
		this.service = service;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		YearsForm ys =  service.findOne(Integer.valueOf(text));
		setValue(ys);
	}
}