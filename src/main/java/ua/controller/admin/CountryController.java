package ua.controller.admin;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static ua.service.utils.ParamBuilder.getParams;

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



import javax.validation.Valid;

import ua.dto.filter.BasicFilter;
import ua.entity.Country;
import ua.service.CountryService;
import ua.validator.CountryValidator;

@Controller
@RequestMapping("/admin/country")
@SessionAttributes(names="country")
public class CountryController {
	
	@Autowired
	private CountryService countryService;
	
	@ModelAttribute("country")
	protected Country getForm(){
		return new Country();
	}
	
	@InitBinder("country")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new CountryValidator(countryService));
	}
	
	@ModelAttribute("filter")
	public BasicFilter getFilter(){
		return new BasicFilter();
	}
	
	
	@RequestMapping
	public String show( Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		model.addAttribute("page", countryService.findAll(filter,pageable));
		return "admin-country";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id,@PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		if((id==1)){
			return "admin-error";
		}
			countryService.removDependencies(id);
			countryService.delete(id);
		
		return "redirect:/admin/country"+getParams(pageable, filter);
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model,@PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		model.addAttribute("country", countryService.findOne(id));
		model.addAttribute("page", countryService.findAll(filter,pageable));
		return "admin-country";
	}
	
	@RequestMapping(method=POST)
	public String save(@ModelAttribute("country")@Valid Country country,BindingResult br,Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		if(br.hasErrors()){
			model.addAttribute("page", countryService.findAll(filter,pageable));
			return "admin-country";
		}
		countryService.save(country);
		status.setComplete();
		return "redirect:/admin/country"+getParams(pageable, filter);
	}
}