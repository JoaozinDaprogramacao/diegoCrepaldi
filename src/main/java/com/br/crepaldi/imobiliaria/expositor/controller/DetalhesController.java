package com.br.crepaldi.imobiliaria.expositor.controller;

import com.br.crepaldi.imobiliaria.expositor.imovel.Imovel;
import com.br.crepaldi.imobiliaria.expositor.imovel.ImovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("detalhes")
public class DetalhesController {

    @Autowired
    private ImovelService service;

    @GetMapping("/{id}")
    public String getHtml(@PathVariable Integer id, Model model) {
        System.out.println(id);

        Imovel imovel = service.getById(id);

        model.addAttribute("imovel", imovel);
        return "detalhesImoveis.html";
    }
}
