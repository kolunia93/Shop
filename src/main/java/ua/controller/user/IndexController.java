package ua.controller.user;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.math.BigDecimal;
import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import ua.dto.filter.ItemFilter;
import ua.dto.form.UserForm;
import ua.editor.CommentEditor;
import ua.editor.ItemEditor;
import ua.entity.Comment;
import ua.entity.Item;
import ua.entity.Order;
import ua.entity.Season;
import ua.entity.User;
import ua.service.CategoryService;
import ua.service.CommentService;
import ua.service.ItemService;
import ua.service.OrderService;
import ua.service.UserService;
import ua.service.VenderService;
import ua.validator.UserValidator;

@Controller
public class IndexController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private VenderService venderService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commenService;
	@Autowired
	private OrderService orderService;
	
	@InitBinder("formm")
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Item.class, new ItemEditor(itemService));
		binder.registerCustomEditor(Comment.class, new CommentEditor(commenService));
		binder.setValidator(new UserValidator(userService));
	}
	
	@ModelAttribute("formm")
	public UserForm getForm(){
		return new UserForm();
	}
	
	@ModelAttribute("item")
	public Item getFormUser(){
		return new Item();
	}
	
	@ModelAttribute("order")
	public Order getFormFinish(){
		return new Order();
	}
	
	@InitBinder("order")
	protected void initBinderOrder(WebDataBinder binder) {
		binder.registerCustomEditor(Item.class, new ItemEditor(itemService));
	}
	
	@InitBinder("comment")
	protected void initBinderComment(WebDataBinder binder) {
		binder.registerCustomEditor(Item.class, new ItemEditor(itemService));
	}
	
	@ModelAttribute("filter")
	public ItemFilter getFilter(){
		return new ItemFilter();
	}
	@ModelAttribute("comment")
	public Comment getFormComment(){
		return new Comment();
	}
	
	@RequestMapping("/deletebuy/{id}") 
	public String deletebuy(@PathVariable int id, Principal principal,@PageableDefault Pageable pageable,@ModelAttribute("filter") ItemFilter filter){ 
		userService.deletebuy(id, principal); 
		return "redirect:/basketUser/"+getParams(pageable, filter); 
	}
	
	@RequestMapping("/order")
	public String order(Principal principal,Model model){ 
		orderService.save(principal);
		model.addAttribute("user", userService.findUserBuy(principal));
		return "user-basketUser"; 
	}
	
	@RequestMapping("/admin")
	public String admin(){
		return "admin-admin";
	}
	
	@RequestMapping("/login")
	public String login(Model model){
		model.addAttribute("venders", venderService.findAll());
		model.addAttribute("categorys", categoryService.findAll());

		return "user-login";
	}
	
	@RequestMapping("/basketUser")
	public String BasketUser(Model model, @PageableDefault Pageable pageable,Principal principal ){
		model.addAttribute("user", userService.findUserBuy(principal));
		BigDecimal price =new BigDecimal(0);
			if(userService.findUserBuy(principal)!=null){
				for(Item item:userService.findUserBuy(principal).getItems()){
					price=price.add(item.getPrice());
				}
		model.addAttribute("venders", venderService.findAll());
		model.addAttribute("categorys", categoryService.findAll());
		model.addAttribute("sum",price);
		}
		return "user-basketUser";
	}
	
	@RequestMapping("/item/{id}")
	public String showItem( @PathVariable int id,Model model){
			model.addAttribute("item", itemService.findById(id));
			model.addAttribute("comments", commenService.findById(id));
			model.addAttribute("venders", venderService.findAll());
			model.addAttribute("categorys", categoryService.findAll());
		return "user-item";
	}
	
	@RequestMapping("/buy/{id}")
	
	public String buy( @PathVariable int id,Principal principal,@PageableDefault Pageable pageable,@ModelAttribute("filter") ItemFilter filter){
			userService.saveItem(principal,id);
		return "redirect:/"+getParams(pageable, filter);
	}

@RequestMapping("/deleteComment/{id}")
	public String deleteCommen( @PathVariable int id,@PageableDefault Pageable pageable,@ModelAttribute("filter") ItemFilter filter){
			commenService.deleteComment(id);
	return "redirect:/"+getParams(pageable, filter);
	}

	
	@RequestMapping("/registration")
	public String registration(Model model){
		model.addAttribute("user", new User());
		model.addAttribute("venders", venderService.findAll());
		model.addAttribute("categorys", categoryService.findAll());
		return "user-registration";
	}
	
	@RequestMapping(value="/registration", method=POST)
	public String registration(@ModelAttribute("formm") @Valid UserForm user, BindingResult br, Model model, SessionStatus status){
		if(br.hasErrors()){
			model.addAttribute("user", new User());
			model.addAttribute("venders", venderService.findAll());
			model.addAttribute("categorys", categoryService.findAll());
			return "user-registration";
		}
		model.addAttribute("venders", venderService.findAll());
		model.addAttribute("categorys", categoryService.findAll());
		userService.save(user);
		status.setComplete();
		return "redirect:/login";
	}
	
	@RequestMapping("/")
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") ItemFilter filter,Principal principal){
		model.addAttribute("page", itemService.findAll(filter,pageable));
		model.addAttribute("user", userService.findUserBuy(principal));
		model.addAttribute("venders", venderService.findAll());
		model.addAttribute("categorys", categoryService.findAll());


		return "user-index";
}
	@RequestMapping(value="/comment", method=POST)
	public String comment(@ModelAttribute("comment")@Valid Comment comment,BindingResult br, Principal principal,@PageableDefault Pageable pageable,@ModelAttribute("filter") ItemFilter filter){
		commenService.saveComment(comment,principal);
		String id=""+comment.getItem().getId();
		return "redirect:/item/"+id+getParams(pageable, filter);
	}
	
	@RequestMapping("/boy")
	public String showBoy(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") ItemFilter filter,Principal principal){
		model.addAttribute("page", itemService.findAllBoy(filter,pageable));
		model.addAttribute("venders", venderService.findAll());
		model.addAttribute("categorys", categoryService.findAll());
		model.addAttribute("user", userService.findUserBuy(principal));
		
		return "user-index";
	}
	@RequestMapping("/girls")
	public String showGirls(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") ItemFilter filter,Principal principal){
		model.addAttribute("page", itemService.findAllGirls(filter,pageable));
		model.addAttribute("venders", venderService.findAll());
		model.addAttribute("categorys", categoryService.findAll());
		model.addAttribute("user", userService.findUserBuy(principal));
System.out.println();
		return "user-index";
	}
	
	@RequestMapping("/category/{id}")
	public String showCategory(@PathVariable int id,Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") ItemFilter filter,Principal principal){
		filter.setCategoryId(id);
		model.addAttribute("page", itemService.findAll(filter,pageable));
		model.addAttribute("venders", venderService.findAll());
		model.addAttribute("categorys", categoryService.findAll());
		model.addAttribute("user", userService.findUserBuy(principal));
		return "user-index";
	}
	
	@RequestMapping("/vender/{id}")
	public String showVender(@PathVariable int id,Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") ItemFilter filter,Principal principal){
		filter.setVenderId(id);
		model.addAttribute("page", itemService.findAll(filter,pageable));
		model.addAttribute("venders", venderService.findAll());
		model.addAttribute("categorys", categoryService.findAll());
		model.addAttribute("user", userService.findUserBuy(principal));
		return "user-index";
	}
	
	@RequestMapping("/season/{season}")
	public String showVender(@PathVariable Season season,Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") ItemFilter filter,Principal principal){
		System.out.println(season);
		filter.setSeasonSerch(season);
		model.addAttribute("page", itemService.findAll(filter,pageable));
		model.addAttribute("venders", venderService.findAll());
		model.addAttribute("categorys", categoryService.findAll());
		model.addAttribute("user", userService.findUserBuy(principal));
		return "user-index";
	}
	
	private String getParams(Pageable pageable, ItemFilter filter) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=");
		buffer.append(String.valueOf(pageable.getPageNumber()+1));
		buffer.append("&size=");
		buffer.append(String.valueOf(pageable.getPageSize()));
		if(pageable.getSort()!=null){
			buffer.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order)->{
				buffer.append(order.getProperty());
				if(order.getDirection()!=Direction.ASC)
				buffer.append(",desc");
			});
			
			}
			if(!filter.getMinSize().isEmpty()){
				buffer.append("&minSize=");
				buffer.append(filter.getMinSize());
			}
			if(!filter.getMaxSize().isEmpty()){
				buffer.append("&maxSize=");
				buffer.append(filter.getMaxSize());
			}
			
			if(!filter.getSearch().isEmpty()){
				buffer.append("&Search=");
				buffer.append(filter.getSearch());
			}
			
			for(Integer id : filter.getCategoryId()){
				buffer.append("&category=");
				
				buffer.append(id);
			}
			
			for(Integer id : filter.getGenderSerch()){
			
				buffer.append("&gender=");
				buffer.append(id);
			}
				for(Integer id : filter.getSeasonSerch()){
			
				buffer.append("&season=");
				buffer.append(id);
			}
					
			for(Integer id : filter.getVenderId()){
				buffer.append("&vender=");
				buffer.append(id);
			}
			
			if(!filter.getMaxPrice().isEmpty()){
				buffer.append("&maxPrice=");
				buffer.append(filter.getMaxPrice());
			}
			if(!filter.getMinPrice().isEmpty()){
				buffer.append("&minPrice=");
				buffer.append(filter.getMinPrice());
			}
		
		return buffer.toString();
	}
	
	
	
}