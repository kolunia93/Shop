package ua.controller.admin;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.dto.filter.ItemFilter;
import ua.dto.form.ItemForm;
import ua.editor.CategoryEditor;
import ua.editor.CommentEditor;
import ua.editor.DescriptEditor;
import ua.editor.GenderEditor;
import ua.editor.NameEditor;
import ua.editor.OrderEditor;
import ua.editor.SeasonEditor;
import ua.editor.SizeEditor;
import ua.editor.VenderEditor;
import ua.editor.YearsEditor;
import ua.entity.Category;
import ua.entity.Comment;
import ua.entity.Descript;
import ua.entity.Gender;
import ua.entity.Item;
import ua.entity.Name;
import ua.entity.Order;
import ua.entity.Season;
import ua.entity.Size;
import ua.entity.User;
import ua.entity.Vender;
import ua.entity.Years;
import ua.service.CategoryService;
import ua.service.CommentService;
import ua.service.DescriptService;
import ua.service.ItemService;
import ua.service.NameService;
import ua.service.OrderService;
import ua.service.SizeService;
import ua.service.UserService;
import ua.service.VenderService;
import ua.service.YearsService;
import ua.validator.ItemValidator;

@Service
@RequestMapping("/admin/item")
@SessionAttributes(names="item")
public class ItemsController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private NameService nameService;
	@Autowired
	private SizeService sizeService;
	@Autowired
	private VenderService venderService;
	@Autowired
	private YearsService yearsService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private DescriptService descriptService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CommentService commentService;

	@InitBinder("item")
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Category.class,new CategoryEditor(categoryService));
		binder.registerCustomEditor(Name.class, new NameEditor(nameService));
		binder.registerCustomEditor(Size.class,new SizeEditor(sizeService));
		binder.registerCustomEditor(Vender.class,new VenderEditor(venderService));
		binder.registerCustomEditor(Years.class,new YearsEditor(yearsService));
		binder.registerCustomEditor(Season.class, new SeasonEditor());
		binder.registerCustomEditor(Descript.class, new DescriptEditor(descriptService));
		binder.registerCustomEditor(Comment.class, new CommentEditor(commentService));
		binder.registerCustomEditor(Order.class, new OrderEditor(orderService));
		binder.registerCustomEditor(Descript.class, new DescriptEditor(descriptService));
		 binder.registerCustomEditor(Gender.class, new GenderEditor());
		binder.setValidator(new ItemValidator(itemService));		
	}
	
	@ModelAttribute("item")
	protected ItemForm getForm(){
		return new ItemForm();
	}
	
	@ModelAttribute("filter")
	public ItemFilter getFilter(){
		return new ItemFilter();
	} 
	
	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") ItemFilter filter){
		System.out.println(filter.getGenderSerch());
		System.out.println(filter.getSeasonSerch());
		model.addAttribute("page", itemService.findAll(filter,pageable));
		model.addAttribute("names", nameService.findAll());
		model.addAttribute("venders", venderService.findAll());
		model.addAttribute("categorys", categoryService.findAll());
		model.addAttribute("sizes", sizeService.findAll());
		model.addAttribute("yearss", yearsService.findAll());
		model.addAttribute("descripts", descriptService.findAll());
		 model.addAttribute( "genders", Gender.values());
		 model.addAttribute( "seasons", Season.values());
		return "admin-item";
	}
	@RequestMapping("/deleteDependency/{id}")
	public String deleteCommen( @PathVariable int id,Model model,@PageableDefault Pageable pageable,@ModelAttribute("filter") ItemFilter filter){
		
		orderService.deletebuy(id);
		 userService.deletebuy(id);
		 commentService.deletebuy(id);
		itemService.delete(id);
		show(model, pageable, filter);
			return "admin-item";
	}
		
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id,Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") ItemFilter filter,Principal principal){
		Item item=itemService.findById(id);
		List<User> user=userService.findByItem(id);
		List<Comment> comments=commentService.findById(id);
		System.out.println(comments.isEmpty());
		System.out.println(user.isEmpty());
		System.out.println(user.size());
		for(User i:user){
		System.out.println(i.getUsername() );
		}
		
		if(item!=null){
		if(!(comments.isEmpty()&&user.isEmpty()&&item.getOrdersItem().isEmpty())){
			model.addAttribute("items", item);
			model.addAttribute("users", user);
			model.addAttribute("comments", comments);			
			model.addAttribute("orders", item.getOrdersItem());						
			return "admin-dlt";
		}
		
		itemService.delete(id);
		}
		return "redirect:/admin/item"+getParams(pageable, filter);
	}
	
	@RequestMapping("/basket")
	public String Basket(Model model, @PageableDefault Pageable pageable ){
		model.addAttribute("orders", orderService.findAll());				
		return "admin-basket";
	}
	
	@RequestMapping("/basketAll")
	public String BasketAll(Model model, @PageableDefault Pageable pageable ){
		System.out.println(orderService.findFinish());
		model.addAttribute("orders", orderService.findFinish());				
		return "admin-basket";
	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") ItemFilter filter){
		model.addAttribute("item", itemService.findOne(id));
		show(model, pageable, filter);
		return "admin-item";
	}
	@RequestMapping("/finish/{id}")
	public String finish(@PathVariable int id, Model model){
		Order order=orderService.findById(id);
		order.setOrder(true);
		orderService.save(order);
		model.addAttribute("orders", orderService.findAll());
		return "admin-basket";
	}
	

	@RequestMapping(method=POST)
	public String save(@ModelAttribute("item") @Valid ItemForm item, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") ItemFilter filter){
		if(br.hasErrors()){
			show(model, pageable, filter);
			return "admin-item";
		}
		itemService.save(item);
		status.setComplete();
		
		return "redirect:/admin/item"+getParams(pageable, filter);
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