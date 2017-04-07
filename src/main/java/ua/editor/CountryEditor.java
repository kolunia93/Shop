package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Country;
import ua.service.CountryService;




public class CountryEditor extends PropertyEditorSupport{

	private final CountryService service;

	public CountryEditor(CountryService service) {
		this.service = service;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Country cs =  service.findOne(Integer.valueOf(text));
		setValue(cs);
	}
}