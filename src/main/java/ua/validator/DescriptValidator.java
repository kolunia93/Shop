package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.Descript;
import ua.service.DescriptService;

public class DescriptValidator implements Validator {

private final DescriptService descriptService;
	
	public DescriptValidator(DescriptService descriptService) {
		this. descriptService = descriptService;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return clazz.equals(Descript.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descript", "", "Can`t be empty");
		}
	

}
