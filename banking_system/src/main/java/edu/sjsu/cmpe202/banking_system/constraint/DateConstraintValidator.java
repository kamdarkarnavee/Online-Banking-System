package edu.sjsu.cmpe202.banking_system.constraint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateConstraintValidator implements ConstraintValidator<ValidDate, String> {

    private static final String DATE_PATTERN = "MM/dd/yyyy";

    @Override
    public void initialize(ValidDate arg0) {
    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext cxt) {
    	 SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN); 
    	 try
	     {
	         sdf.setLenient(false);
	         sdf.parse(date);
	         return true;
	     }
	     catch (ParseException e)
	     {
	         return false;
	     }
    }

}