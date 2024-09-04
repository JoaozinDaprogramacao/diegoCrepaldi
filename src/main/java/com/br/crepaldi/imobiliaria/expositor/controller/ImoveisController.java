package com.br.crepaldi.imobiliaria.expositor.controller;

import com.br.crepaldi.imobiliaria.expositor.imovel.Imovel;
import com.br.crepaldi.imobiliaria.expositor.imovel.ImovelDto;
import com.br.crepaldi.imobiliaria.expositor.imovel.ImovelService;
import com.br.crepaldi.imobiliaria.expositor.imovel.TipoImovel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("imoveis")
public class ImoveisController {

    @Autowired
    private ImovelService imovelService;

    @GetMapping
    public String getHtml() {
        return "buscarImoveis.html";
    }

    @GetMapping("/pesquisa")
    public String pesquisaImoveis(@RequestParam("tipo-imovel") String tipoImovelParam,
                                  @RequestParam(value="input-pesquisa", required = false) String inputPesquisa,
                                  @RequestParam(value = "valormin", required = false) String valormin,
                                  @RequestParam(value = "valormax", required = false) String valormax,
                                  @RequestParam(value = "quartosmin", required = false) String quartosmin,
                                  @RequestParam(value = "quartosmax", required = false) String quartosmax,
                                  @RequestParam(value = "areamin", required = false) String areamin,
                                  @RequestParam(value = "areamax", required = false) String areamax,
                                  @RequestParam(value = "banheirosmin", required = false) String banheirosmin,
                                  @RequestParam(value = "banheirosmax", required = false) String banheirosmax,
                                  Model model) {

        if (inputPesquisa == null || inputPesquisa.trim().isEmpty()) {
            model.addAttribute("error", "O campo de pesquisa não pode estar vazio.");
            return "buscarImoveis";
        }

        // Converte os valores para BigDecimal com tratamento de erro
        BigDecimal valorMin = valormin != null && !valormin.isEmpty() ? new BigDecimal(valormin) : BigDecimal.ZERO;
        BigDecimal valorMax = valormax != null && !valormax.isEmpty() ? new BigDecimal(valormax) : new BigDecimal("9999999999999999");
        BigDecimal quartosMin = quartosmin != null && !quartosmin.isEmpty() ? new BigDecimal(quartosmin) : BigDecimal.ZERO;
        BigDecimal quartosMax = quartosmax != null && !quartosmax.isEmpty() ? new BigDecimal(quartosmax) : new BigDecimal("9999999999999999");
        BigDecimal areaMin = areamin != null && !areamin.isEmpty() ? new BigDecimal(areamin) : BigDecimal.ZERO;
        BigDecimal areaMax = areamax != null && !areamax.isEmpty() ? new BigDecimal(areamax) : new BigDecimal("9999999999999999");
        BigDecimal banheirosMin = banheirosmin != null && !banheirosmin.isEmpty() ? new BigDecimal(banheirosmin) : BigDecimal.ZERO;
        BigDecimal banheirosMax = banheirosmax != null && !banheirosmax.isEmpty() ? new BigDecimal(banheirosmax) : new BigDecimal("9999999999999999");

        // Verifica valores no dto
        System.out.println("dto.banheirosmin: " + banheirosMin);
        System.out.println("dto.banheirosmax: " + banheirosMax);


        ImovelDto dto = new ImovelDto(
                TipoImovel.valueOf(tipoImovelParam),
                inputPesquisa,
                valorMin,
                valorMax,
                quartosMin,
                quartosMax,
                banheirosMin,
                banheirosMax,
                areaMin,
                areaMax,
                null, null);

        // Verifica valores no dto
        System.out.println("dto.banheirosmin: " + dto.banheirosmin());
        System.out.println("dto.banheirosmax: " + dto.banheirosmax());

        List<Imovel> imoveis = imovelService.findImoveisComCascata(dto);

        model.addAttribute("imoveis", imoveis);

        return "buscarImoveis";
    }




}
