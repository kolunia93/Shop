package ua.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

import ua.dto.filter.BasicFilter;
import ua.entity.Name;
import ua.service.NameService;
import ua.validator.NameValidator;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static ua.service.utils.ParamBuilder.getParams;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin/name")
@SessionAttributes(names="name")
public class NameController {
	
	@Autowired
	private NameService nameService;
	
	@ModelAttribute("name")
	protected Name getForm(){
		return new Name();
	}
	
	@InitBinder("name")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new NameValidator(nameService));
	}
	
	@ModelAttribute("filter")
	public BasicFilter getFilter(){
		return new BasicFilter();
	}
	
	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		model.addAttribute("page", nameService.findAll(filter,pageable));
		return "admin-name";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id,@PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		if((id==1)){
			return "admin-error";
		}
			nameService.removDependencies(id);
		nameService.delete(id);
		return "redirect:/admin/name"+getParams(pageable, filter);
	}
	

	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model,@PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		model.addAttribute("name",nameService.findOne(id));
		model.addAttribute("page", nameService.findAll(filter,pageable));
		return "admin-name";
	}
	
	@RequestMapping(method=POST)
	public String save(@ModelAttribute("name")@Valid Name name,BindingResult br,Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		if(br.hasErrors()){
			model.addAttribute("page", nameService.findAll(filter,pageable));
			return "admin-name";
		}
		nameService.save(name);
		status.setComplete();
		return "redirect:/admin/name"+getParams(pageable, filter);
	}
}