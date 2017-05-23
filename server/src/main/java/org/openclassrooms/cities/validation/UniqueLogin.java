package org.openclassrooms.cities.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by jguidoux on 23/05/2017.
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueLoginValidator.class)
public @interface UniqueLogin {
    String message() default "{org.openclassrooms.cities.UniqueLogin.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
