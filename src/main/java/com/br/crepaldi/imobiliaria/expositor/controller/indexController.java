package com.br.crepaldi.imobiliaria.expositor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class indexController {

    @GetMapping
    public String getHtml() {
        return "index.html";
    }

}
