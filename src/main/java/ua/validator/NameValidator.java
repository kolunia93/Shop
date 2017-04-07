package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.Name;
import ua.service.NameService;

public class NameValidator implements Validator{

	private final NameService nameService;
	
	public NameValidator(NameService nameService) {
		this. nameService = nameService;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return clazz.equals(Name.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Name name = (Name) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
		if(nameService.findOne(name.getName())!=null){
			errors.rejectValue("name", "", "Already exist");
		}
	}
}
