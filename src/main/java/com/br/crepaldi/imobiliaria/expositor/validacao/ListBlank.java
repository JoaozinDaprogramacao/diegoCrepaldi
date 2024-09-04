package com.br.crepaldi.imobiliaria.expositor.validacao;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ListValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ListBlank {
    String message()  default "Lista Vazia.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default{};
}
