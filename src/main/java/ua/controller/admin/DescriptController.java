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
import ua.entity.Descript;
import ua.service.DescriptService;
import ua.validator.DescriptValidator;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static ua.service.utils.ParamBuilder.getParams;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin/descript")
@SessionAttributes(names="descript")
public class DescriptController {
	
	@Autowired
	private DescriptService descriptService;
	
	@ModelAttribute("descript")
	protected Descript getForm(){
		return new Descript();
	}
	
	@InitBinder("descript")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new DescriptValidator(descriptService));
	}
	
	@ModelAttribute("filter")
	public BasicFilter getFilter(){
		return new BasicFilter();
	}
	
	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		model.addAttribute("page", descriptService.findAll(filter,pageable));
		return "admin-descript";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id,@PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		if((id==1)){
			return "admin-error";
		}
			descriptService.removDependencies(id);
		descriptService.delete(id);
		return "redirect:/admin/descript"+getParams(pageable, filter);
	}
	

	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model,@PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		model.addAttribute("descript",descriptService.findOne(id));
		model.addAttribute("page", descriptService.findAll(filter,pageable));
		return "admin-descript";
	}
	
	@RequestMapping(method=POST)
	public String save(@ModelAttribute("descript")@Valid Descript descript,BindingResult br,Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		if(br.hasErrors()){
			model.addAttribute("page", descriptService.findAll(filter,pageable));
			return "admin-descript";
		}
		descriptService.save(descript);
		status.setComplete();
		return "redirect:/admin/descript"+getParams(pageable, filter);
	}
}