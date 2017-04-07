package ua.controller.admin;

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

import ua.dto.filter.VenderFilter;
import ua.editor.CountryEditor;
import ua.entity.Country;
import ua.entity.Vender;
import ua.service.CountryService;
import ua.service.VenderService;
import ua.validator.VenderValidator;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import javax.validation.Valid;


@Controller
@RequestMapping("/admin/vender")
@SessionAttributes(names="vender")
public class VenderController {
	
	@Autowired
	private VenderService venderService;
	@Autowired
	private CountryService countryService;
	
	@InitBinder("vender")
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Country.class,new CountryEditor(countryService) );
		   binder.setValidator(new VenderValidator(venderService));
	}
	
	@ModelAttribute("vender")
	protected Vender getForm(){
		return new Vender();
	}
	
	@ModelAttribute("filter")
	public VenderFilter getFilter(){
		return new VenderFilter();
	}
	
	@RequestMapping
	public String show( Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") VenderFilter filter){
		model.addAttribute("page", venderService.findAll(filter,pageable));
		model.addAttribute("countrys", countryService.findAll());
		return "admin-vender";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") VenderFilter filter){
		System.out.println(id==1);
		System.out.println(id);	
		if((id==1)){
			return "admin-error";
		}
		venderService.removDependencies(id);
		venderService.delete(id);
		return "redirect:/admin/vender"+getParams(pageable, filter);
	}
	
	@RequestMapping("/update/{id}")
	public String update( @PathVariable int id, Model model,@PageableDefault Pageable pageable, @ModelAttribute("filter") VenderFilter filter){
		model.addAttribute("vender" , venderService.findOne(id));
		model.addAttribute("page" , venderService.findAll(filter,pageable));
		model.addAttribute("countrys", countryService.findAll());
		return "admin-vender";
	}
	
	@RequestMapping(method=POST)
	public String save(@ModelAttribute("vender")@Valid Vender vender,BindingResult br,Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") VenderFilter filter){
		if(br.hasErrors()){
			model.addAttribute("page" , venderService.findAll(filter,pageable));
			model.addAttribute("countrys", countryService.findAll());
			return "admin-vender";
		}
		venderService.save(vender);
		status.setComplete();
		return "redirect:/admin/vender"+getParams(pageable, filter);
	}
	
	private String getParams(Pageable pageable, VenderFilter filter) {
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
			if(!filter.getVender().isEmpty()){
				buffer.append("&vender=");
				buffer.append(filter.getVender());
			}
			for(Integer id : filter.getCountryId()){
				buffer.append("&country=");
				buffer.append(id);
			
		}
		}
		return buffer.toString();
	
	}
}
