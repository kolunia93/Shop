package ua.controller.user;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.security.Principal;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.entity.Comment;
import ua.entity.Item;
import ua.service.CommentService;

public class CommentController {

	CommentService commentService;
	
//	@RequestMapping("/comment/{comment}&{id}")
//	public String comment(@PathVariable String comment,@PathVariable int id,Principal principal){
//			System.out.println("-----------------------------");
//		
//		return "redirect:/";
//	}
}
