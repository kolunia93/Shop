package ua.service;

import java.security.Principal;
import java.util.List;

import ua.entity.Comment;

public interface CommentService {

	void saveComment( Comment comment,Principal principal);

	List<Comment> findAll();

	List<Comment> findById(int id);

	void deleteComment(int id);

	Comment findOne(Integer id);

	List<Comment> findByUser(Principal principal);

	void deletebuy(int id);


}
