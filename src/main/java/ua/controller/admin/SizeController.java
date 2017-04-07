package ua.controller.admin;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.dto.filter.SizeFilter;
import ua.dto.form.SizeForm;
import ua.service.SizeService;
import ua.validator.SizeValidator;



@Controller
@RequestMapping("/admin/size")
@SessionAttributes(names="size")
public class SizeController {
	
	@Autowired
	private SizeService sizeService;
	
	@ModelAttribute("size")
	protected SizeForm getForm(){
		return new SizeForm();
	}
	
	@RequestMapping
	public String show(Model model,@PageableDefault Pageable pageable, @ModelAttribute("filter") SizeFilter filter){
		model.addAttribute("page", sizeService.findAll(filter,pageable));
		return "admin-size";
	}
	
	@InitBinder("size")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new SizeValidator(sizeService));
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id,@PageableDefault Pageable pageable, @ModelAttribute("filter") SizeFilter filter){
		if((id==1)){
			return "admin-error";
		}
		sizeService.removDependencies(id);
		sizeService.delete(id);
		return "redirect:/admin/size"+getParams(pageable, filter);
	}
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model,@PageableDefault Pageable pageable, @ModelAttribute("filter") SizeFilter filter){
		model.addAttribute("size",sizeService.findOne(id));
		model.addAttribute("page", sizeService.findAll(filter,pageable));
		return "admin-size";
	}
	
	@RequestMapping(method=POST)
	public String save(@ModelAttribute("size")@Valid SizeForm size,BindingResult br,Model model, SessionStatus status,@PageableDefault Pageable pageable, @ModelAttribute("filter") SizeFilter filter){
		if(br.hasErrors()){
			model.addAttribute("page", sizeService.findAll(filter,pageable));
			return "admin-size";
		}
		sizeService.save(size);
		status.setComplete();
		return "redirect:/admin/size"+getParams(pageable, filter);
	}

	private String getParams(Pageable pageable, SizeFilter filter) {
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
			if(!filter.getMaxSize().isEmpty()){
				buffer.append("&maxPrice=");
				buffer.append(filter.getMaxSize());
			}
			if(!filter.getMinSize().isEmpty()){
				buffer.append("&minPrice=");
				buffer.append(filter.getMinSize());
			}
		}
		return buffer.toString();
	}
}