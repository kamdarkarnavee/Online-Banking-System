package edu.sjsu.cmpe202.banking_system.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AccountNumberConstraintValidator implements ConstraintValidator<ValidAccountNumber, Long> {

    @Override
    public void initialize(ValidAccountNumber arg0) {
    }

    @Override
    public boolean isValid(Long number, ConstraintValidatorContext cxt) {
        String numString = Long.toString(number);
        if (number != null && numString.matches("[0-9]+")
                && (numString.length() ==10)) {
            return true;
        } else {
            return false;
        }
    }
}
