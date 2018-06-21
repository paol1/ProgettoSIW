package it.uniroma3.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.model.Centro;

@Component
public class CentroValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Centro.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "indirizzo", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numTelefono", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "capienza", "required");
        
        try {
        	Integer.parseInt((String) errors.getFieldValue("numTelefono"));
        }
        catch (NumberFormatException e) {
        	errors.rejectValue("numTelefono", "numeric");
        }
        
        try {
        	Integer.parseInt((String) errors.getFieldValue("capienza"));
        }
        catch (NumberFormatException e) {
        	errors.rejectValue("capienza", "numeric");
        }
       
	}

}
