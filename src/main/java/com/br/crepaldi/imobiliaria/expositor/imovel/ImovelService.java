package com.br.crepaldi.imobiliaria.expositor.imovel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository imovelRepository;

    public List<Imovel> findImoveisComCascata(ImovelDto dto) {

    List<Imovel> imoveis = imovelRepository.findAll();

     imoveis = VerificaEmCascata(imoveis, dto);


     return imoveis;
    }

    private List<Imovel> VerificaEmCascata(List<Imovel> imoveis, ImovelDto dto) {

        imoveis = imoveis.stream()
                .filter(imovel -> imovel.getInputPesquisa().equals(dto.inputPesquisa()))
                .toList();

        imoveis = imoveis.stream()
                .filter(imovel -> imovel.getTipoImovel().equals(dto.tipoImovel()))
                .toList();

        imoveis =  imoveis.stream()
                .filter(imovel -> {
                    double preco = imovel.getPreco().doubleValue();
                    double valorMin = dto.valormin().doubleValue();
                    double valorMax = dto.valormax().doubleValue();
                    return preco >= valorMin && preco <= valorMax;
                })
                .collect(Collectors.toList());

        imoveis =  imoveis.stream()
                .filter(imovel -> {
                    double quartos = imovel.getQuartos().doubleValue();
                    double quartosMin = dto.quartosmin().doubleValue();
                    double quartosMax = dto.quartosmax().doubleValue();
                    return quartos >= quartosMin && quartos <= quartosMax;
                })
                .collect(Collectors.toList());


        imoveis =  imoveis.stream()
                .filter(imovel -> {
                    double banheiros = imovel.getBanheiros().doubleValue();
                    double banheirosMin = dto.banheirosmin().doubleValue();
                    double banheirosMax = dto.banheirosmax().doubleValue();
                    return banheiros >= banheirosMin && banheiros <= banheirosMax;
                })
                .collect(Collectors.toList());

        imoveis =  imoveis.stream()
                .filter(imovel -> {
                    double area = imovel.getArea().doubleValue();
                    double areaMin = dto.areamin().doubleValue();
                    double areaMax = dto.areamax().doubleValue();
                    return area >= areaMin && area <= areaMax;
                })
                .collect(Collectors.toList());



        return imoveis;

    }

    public Imovel getById(Integer id) {
        return imovelRepository.getReferenceById(id);
    }
}
