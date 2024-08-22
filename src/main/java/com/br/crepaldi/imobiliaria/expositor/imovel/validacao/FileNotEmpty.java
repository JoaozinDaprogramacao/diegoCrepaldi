package com.br.crepaldi.imobiliaria.expositor.imovel.validacao;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FileNotEmptyValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FileNotEmpty {
    String message() default "O arquivo não pode estar vazio.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
