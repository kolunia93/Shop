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

import ua.dto.filter.YearsFilter;
import ua.dto.form.YearsForm;
import ua.service.YearsService;
import ua.validator.YearsValidator;



@Controller
@RequestMapping("/admin/years")
@SessionAttributes(names="years")
public class YearsController {
	
	@Autowired
	private YearsService yearsService;
	
	@ModelAttribute("years")
	protected YearsForm getForm(){
		return new YearsForm();
	}
	
	@InitBinder("years")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new YearsValidator(yearsService));
	}
	
	@RequestMapping
	public String show(Model model,@PageableDefault Pageable pageable, @ModelAttribute("filter") YearsFilter filter){
		model.addAttribute("page", yearsService.findAll(filter,pageable));
		return "admin-years";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id,@PageableDefault Pageable pageable, @ModelAttribute("filter") YearsFilter filter){
		if((id==1)){
			return "admin-error";
		}
			yearsService.removDependencies(id);
		yearsService.delete(id);
		return "redirect:/admin/years"+getParams(pageable, filter);
	
	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id,Model model,@PageableDefault Pageable pageable, @ModelAttribute("filter") YearsFilter filter){
		model.addAttribute("years",yearsService.findOne(id));
		System.out.println(yearsService.findOne(id)+"----------------------------");
		model.addAttribute("page", yearsService.findAll(filter,pageable));
		return "admin-years";
	}
	
	@RequestMapping(method=POST)
	public String save(@ModelAttribute("years")@Valid YearsForm years,BindingResult br,Model model, SessionStatus status,@PageableDefault Pageable pageable, @ModelAttribute("filter") YearsFilter filter){
		if(br.hasErrors()){
			model.addAttribute("page", yearsService.findAll(filter,pageable));
			return "admin-years";
		}
		yearsService.save(years);
		status.setComplete();
		return "redirect:/admin/years"+getParams(pageable, filter);
	}
	
	private String getParams(Pageable pageable, YearsFilter filter) {
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
			if(!filter.getMaxYearsSerch().isEmpty()){
				buffer.append("&maxYearsSerch=");
				buffer.append(filter.getMaxYearsSerch());
			}
			if(!filter.getMinYearsSerch().isEmpty()){
				buffer.append("&minYearsSerch=");
				buffer.append(filter.getMinYearsSerch());
			}
		}
		return buffer.toString();
	}
}