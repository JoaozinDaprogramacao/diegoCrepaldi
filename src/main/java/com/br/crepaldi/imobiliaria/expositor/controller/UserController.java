package com.br.crepaldi.imobiliaria.expositor.controller;

import com.br.crepaldi.imobiliaria.expositor.Exceptions.UsernameAlreadyExistsException;
import com.br.crepaldi.imobiliaria.expositor.Users.UserService;
import com.br.crepaldi.imobiliaria.expositor.Users.dtos.request.UserDTORequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/cadastrar")
    public String getHtml(Model model) {
        model.addAttribute("userDTO", new UserDTORequest("", ""));
        return "cadastrar.html";
    }

    @PostMapping("/cadastrar")
    public String cadastrarUsuario(@Valid @ModelAttribute("userDTO") UserDTORequest dto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            System.out.println(result.getAllErrors());
            return "cadastrar"; // Retorna para a página de cadastro com erros
        }

        try {
            service.createUser(dto.username(), dto.password(), "admin");
        } catch (UsernameAlreadyExistsException e) {
            result.rejectValue("username", "error.userDTO", e.getMessage());
            model.addAttribute("errors", result.getAllErrors()); // Adiciona o erro ao modelo
            return "cadastrar"; // Retorna para a página de cadastro com a mensagem de erro
        }

        return "redirect:/login";
    }


}
