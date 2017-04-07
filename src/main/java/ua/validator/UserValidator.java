package ua.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.dto.form.UserForm;
import ua.service.UserService;

public class UserValidator implements Validator{
 
    private Pattern pattern;
    private Matcher matcher;
 
    private static final String EMAIL_PATTERN =
                    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
 
    private final UserService userService;
	
	public UserValidator(UserService userService) {
		
		this.userService = userService;
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return clazz.equals(UserForm.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserForm user=(UserForm)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "", "Can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fone", "", "Can`t be empty");
		matcher = pattern.matcher(user.getEmail());
		boolean p=true;
		if(userService.findOne(user.getEmail())!=null){
			errors.rejectValue("email", "", "на данний емейл зареєстрована сторінка");
			p=false;
		}
		if(userService.finaByName(user.getUsername())!=null){
			errors.rejectValue("email", "", "Імя користувача зайняте");
			p=false;
		}
		if(userService.finaByFone(user.getFone())!=null){
			errors.rejectValue("fone", "", "Данний телефон привязаний до іньшої сторінки");
			p=false;
		}
		if(user.getPassword().length()<6){
			errors.rejectValue("password", "", "Довжина пароля не повинна бути меньше 6 символів");
			p=false;
		}if(p){

			try {
				userService.cheackMail(user.getEmail());
			} catch (MessagingException e) {
				errors.rejectValue("Email", "", "Не вдалось надіслати лист на пошту");
				e.printStackTrace();
			}
		
		
		}
	}
 
    public boolean validate(final String hex) {
        matcher = pattern.matcher(hex);
 
        return matcher.matches();
    }
 
}