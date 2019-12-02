package edu.sjsu.cmpe202.banking_system.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberConstraintValidator implements ConstraintValidator<ValidPhoneNumber, String> {

    private static final String PHONE_NUMBER_PATTERN = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$";

    @Override
    public void initialize(ValidPhoneNumber arg0) {
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext cxt) {
    	 if(phone.matches(PHONE_NUMBER_PATTERN)) {
	         return true;
	     }
    	 else {
	         return false;
	     }
    }
}