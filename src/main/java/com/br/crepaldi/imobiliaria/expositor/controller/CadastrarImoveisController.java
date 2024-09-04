package com.br.crepaldi.imobiliaria.expositor.controller;

import com.br.crepaldi.imobiliaria.expositor.imovel.cadastrar.CadastrarImoveisService;
import com.br.crepaldi.imobiliaria.expositor.imovel.cadastrar.ImovelDtoCadastroParam;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("cadastrar/imoveis")
public class CadastrarImoveisController {

    @Autowired
    private CadastrarImoveisService service;

    @GetMapping
    public String getHtml(Model model) {
        model.addAttribute("param", new ImovelDtoCadastroParam("", "", "",
                "", "", "",
                "", null, "",
                ""));
        return "adicionarImoveis";
    }

    @PostMapping
    public String cadastrarImovel(@Valid ImovelDtoCadastroParam param, BindingResult result, Model model) throws IOException {
        model.addAttribute("param", param);

        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            model.addAttribute("errors", result.getAllErrors());
            System.out.println(result.getAllErrors());
            return "adicionarImoveis"; // Retorna o HTML para mostrar os erros
        }

        service.cadastrar(param);
        return "redirect:/imoveis"; // Redireciona para o endpoint /buscarimoveis
    }

}
