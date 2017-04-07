package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Comment;
import ua.service.CommentService;

public class CommentEditor extends PropertyEditorSupport{

	private final CommentService service;

	public CommentEditor(CommentService service) {
		this.service = service;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Comment cs =  service.findOne(Integer.valueOf(text));
		setValue(cs);
	}
}