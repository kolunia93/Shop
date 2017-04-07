package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.User;
import ua.service.UserService;

public class UserEditor extends PropertyEditorSupport{

	private final UserService service;

	public UserEditor(UserService service) {
		this.service = service;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		User cs =  service.findOne(Integer.valueOf(text));
		setValue(cs);
	}
}