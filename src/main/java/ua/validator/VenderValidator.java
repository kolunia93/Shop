package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.Vender;
import ua.service.VenderService;

	public class VenderValidator implements Validator{
	
		private final VenderService venderService;
		
		public VenderValidator(VenderService venderService) {
			this. venderService = venderService;
		}
		
		@Override
		public boolean supports(Class<?> clazz) {
			
			return clazz.equals(Vender.class);
		}

		@Override
		public void validate(Object target, Errors errors) {
			Vender vender = (Vender) target;
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vender", "", "Can`t be empty");
			if(venderService.findOne(vender.getVender())!=null){
				errors.rejectValue("vender", "", "Already exist");
			}
			
		}
}