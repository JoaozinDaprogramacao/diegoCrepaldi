package com.br.crepaldi.imobiliaria.expositor.validacao;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ListValidator implements ConstraintValidator<ListBlank, List<MultipartFile>> {
    @Override
    public boolean isValid(List<MultipartFile> value, ConstraintValidatorContext context) {
        return value != null && !value.isEmpty();
    }
}
