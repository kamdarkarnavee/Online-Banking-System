package edu.sjsu.cmpe202.banking_system.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AccountNumberConstraintValidator implements ConstraintValidator<ValidAccountNumber, String> {

    @Override
    public void initialize(ValidAccountNumber arg0) {
    }

    @Override
    public boolean isValid(String number, ConstraintValidatorContext cxt) {
        if (number != null && number.matches("[0-9]+")
                && (number.length() ==10)) {
            return true;
        } else {
            return false;
        }
    }
}
