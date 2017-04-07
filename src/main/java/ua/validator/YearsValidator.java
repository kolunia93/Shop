package ua.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.dto.form.YearsForm;
import ua.service.YearsService;

public class YearsValidator implements Validator{
	
	private final YearsService yearsService;
	
	private static final Pattern PEATTERN = Pattern.compile("[0-9]+");
	boolean p;
	int min;
	int max;
	
	public YearsValidator(YearsService yearsService) {
		super();
		this.yearsService = yearsService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return YearsForm.class.equals( clazz);
		}

	@Override
	public void validate(Object target, Errors errors) {
		YearsForm years = (YearsForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "minYears", "", "Can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "maxYears", "", "Can`t be empty");
		
		if(PEATTERN.matcher(years.getMinYears()).matches()){min = Integer.valueOf(years.getMinYears());}
		else{
		p=false;
			errors.rejectValue("minYears", "", "Enter valid value of min");	
		}
		
		if(PEATTERN.matcher(years.getMaxYears()).matches()){max = Integer.valueOf(years.getMaxYears());}
		else{
			p=false;
			errors.rejectValue("MaxYears", "", "Enter valid value of Max");
		}
		if(p){
		if(min<0||min>16){
			errors.rejectValue("minYears", "", "Enter valid value years min");
		}
		if(max<0||min>16){
			errors.rejectValue("maxYears", "", "Enter valid value of years max");
		}
		if(min>max){
			errors.rejectValue("maxYears", "", "years min cant be bigger then years max");
		}
		
		}
			
		
		
	
	}

}
