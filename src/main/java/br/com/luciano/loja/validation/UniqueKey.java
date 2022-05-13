package br.com.luciano.loja.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueKeyValidator.class)
@Documented
public @interface UniqueKey {
    String columnName();
    String className();
    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
