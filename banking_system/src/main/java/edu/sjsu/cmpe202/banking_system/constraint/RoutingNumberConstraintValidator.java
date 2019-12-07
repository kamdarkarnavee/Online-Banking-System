package edu.sjsu.cmpe202.banking_system.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RoutingNumberConstraintValidator implements ConstraintValidator<ValidRoutingNumber, String> {

    private static final String PHONE_NUMBER_PATTERN = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$";

    @Override
    public void initialize(ValidRoutingNumber arg0) {
    }

    @Override
    public boolean isValid(String number, ConstraintValidatorContext cxt) {
        if (number != null && number.matches("[0-9]+")
                && (number.length() > 5) && (number.length() < 10)) {
            return true;
        } else {
            return false;
        }
    }
}
