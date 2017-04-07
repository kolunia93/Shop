package ua.service.implementation;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Comment;
import ua.entity.Item;
import ua.entity.User;
import ua.repository.CommentRepository;
import ua.repository.ItemsRepository;
import ua.repository.UserRepository;
import ua.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ItemsRepository itemRepository;
	
	@Transactional
	@Override
	public void saveComment(Comment comment, Principal principal) {
		User user=userRepository.findByUsername(principal.getName());
		comment.setUser(user);
		commentRepository.save(comment);
	}

	@Override
	public List<Comment> findAll() {
		return commentRepository.findAll();
	}

	@Override
	public List<Comment> findById(int id) {		
		return commentRepository.findById(id);
	}

	@Transactional
	@Override
	public void deleteComment(int id) {
		Comment comment=commentRepository.findOne(id);
		User user=userRepository.findByComment(id);
		Item item=itemRepository.findByComment(id);
		user.deleteComment(comment);
		item.deleteComment(comment);
		userRepository.save(user);
		itemRepository.save(item);
		commentRepository.delete(comment);
	
	}

	@Override
	public Comment findOne(Integer id) {	
		return commentRepository.findOne(id);
	}

	@Override
	public List<Comment> findByUser(Principal principal) {
		String name=principal.getName();
		return commentRepository.findByUser(name);
	}

	@Transactional
	@Override
	public void deletebuy(int id) {

		List<Comment> comment = commentRepository.findByItems(id);
		for(Comment c:comment){
			deleteComment(c.getId());
	
		}
	} 
}
