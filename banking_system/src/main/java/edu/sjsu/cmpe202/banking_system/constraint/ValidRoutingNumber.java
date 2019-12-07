package edu.sjsu.cmpe202.banking_system.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = RoutingNumberConstraintValidator.class)
@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface ValidRoutingNumber {

    String message() default "Invalid Number. Number must be <0-9>";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

