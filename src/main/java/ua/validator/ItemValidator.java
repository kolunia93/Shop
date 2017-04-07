package ua.validator;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.dto.form.ItemForm;
import ua.service.ItemService;

public class ItemValidator implements Validator {

private final ItemService itemService;

private Double minYears;
private int maxYears;
private static final Pattern PEATTERN = Pattern.compile("[0-9]+");
	public ItemValidator(ItemService itemService) {
	this.itemService = itemService;
	
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return ItemForm.class.equals(clazz);
		}

	@Override
	public void validate(Object target, Errors errors) {
		
		ItemForm item = (ItemForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "", "Can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "remain", "", "Can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "minYears", "", "Can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "maxYears", "", "Can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sizeMin", "", "Can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sizeMin", "", "Can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
		
		try{
		BigDecimal price=new BigDecimal(item.getPrice().replace(',', '.'));
		BigDecimal min=new BigDecimal(0);
		int tr=price.compareTo(min);
		int rem=Integer.parseInt(item.getRemain());
		if(tr==-1){
			errors.rejectValue("price", "", "Price can*t by low them zirrow");
		}
		if(rem<0){
			errors.rejectValue("remain", "", "Remain  can*t by low them zirrow");
		}
		}catch(NumberFormatException e){
			errors.rejectValue("remain", "", "Wrong format of number from remain or price");
		}
		
		if(PEATTERN.matcher(item.getMinYears()).matches()){minYears = Double.valueOf(item.getMinYears());}
		else{
			errors.rejectValue("minYears", "", "Enter valid value of min");
		}
		if(PEATTERN.matcher(item.getMaxYears()).matches()){maxYears = Integer.valueOf(item.getMaxYears());}
		else{
			errors.rejectValue("maxYears", "", "Enter valid value of max");
		}
		
		
		
		if(minYears<0||minYears>220){
			errors.rejectValue("sizeMin", "", "Enter valid value size min");
		}
			
		if(maxYears<0||minYears>220){
			errors.rejectValue("sizeMax", "", "years min cant be bigger then size max");
		}
		if(minYears>maxYears){
			errors.rejectValue("maxYears", "", "Enter valid value of years max");
		}
		
		try{
			double minSize=(Double.valueOf(item.getSizeMin()));
			int maxSize=(Integer.valueOf(item.getSizeMax()));
		if(minSize<0||minSize>16){
			
		}
			
		if(maxSize<0||minSize>16){
			errors.rejectValue("sizeMax", "", "Enter valid value of size max");
		}
		if(minSize>maxSize){
			errors.rejectValue("sizeMax", "", "Size min cant be bigger then years max");
		}
			
		}catch(NumberFormatException e){
			errors.rejectValue("sizeMax", "", "Wrong format of number from size");
		}	
	}
}
