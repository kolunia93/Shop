package ua.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.dto.form.SizeForm;
import ua.service.SizeService;

public class SizeValidator implements Validator {

private final SizeService sizeService;

private static final Pattern PEATTERN = Pattern.compile("[0-9]+");

int min;

int max;
	
	public SizeValidator(SizeService sizeService) {
		super();
		this.sizeService = sizeService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return SizeForm.class.equals(clazz);
		}

	@Override
	public void validate(Object target, Errors errors) {
		SizeForm size = (SizeForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "minSize", "", "Can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "maxSize", "", "Can`t be empty");
		
			if(PEATTERN.matcher(size.getMinSize()).matches()){min = Integer.valueOf(size.getMinSize());}
			else{
				errors.rejectValue("minSize", "", "Enter valid value of min");
			}
			if(PEATTERN.matcher(size.getMaxSize()).matches()){max = Integer.valueOf(size.getMaxSize());}
			else{
				errors.rejectValue("maxSize", "", "Enter valid value of max");
			}
		
		if(min<0||min>220){
			errors.rejectValue("maxSize", "", "Enter valid value size min");
		}
		if(max<0||min>220){
			errors.rejectValue("maxSize", "", "Enter valid value of size max");
		}
		if(min>max){
			errors.rejectValue("maxSize", "", "Size min cant be bigger then Size max");
		}
		
		
	
	}

}
