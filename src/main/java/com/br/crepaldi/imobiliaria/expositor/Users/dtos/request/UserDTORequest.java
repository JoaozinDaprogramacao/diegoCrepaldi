package com.br.crepaldi.imobiliaria.expositor.Users.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record UserDTORequest(

        @NotBlank(message = "Digite um usuário")
        String username,
        @NotBlank(message = "Digite uma senha")
        String password

) {
}
