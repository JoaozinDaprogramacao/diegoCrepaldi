package com.br.crepaldi.imobiliaria.expositor.imovel.cadastrar;

import com.br.crepaldi.imobiliaria.expositor.imovel.Imovel;
import com.br.crepaldi.imobiliaria.expositor.imovel.TipoImovel;
import com.br.crepaldi.imobiliaria.expositor.imovel.validacao.FileNotEmpty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public record ImovelDtoCadastroParam(
        @NotBlank(message = "Selecione o tipo do imóvel.")
        String tipoImovel,
        @NotBlank(message = "Selecione a cidade.")
        String inputPesquisa,
        @NotBlank(message ="Digite o preço do imóvel.")
        String preco,
        @NotBlank(message = "Insira a quantidade de quartos.")
        String quartos,
        @NotBlank(message = "Insira a quatidade de banheiros.")
        String banheiros,
        @NotBlank(message = "Escreva a area do imóvel.")
        String area,
        @NotBlank(message = "Escreva o titulo do imóvel.")
        String titulo,
        @FileNotEmpty(message = "Por favor, faça o upload de uma imagem.")
        MultipartFile imagem,
        @NotBlank(message = "Escreva detalhes os detalhes.")
        String detalhes,
        @NotBlank(message = "Insira uma descrição.")
        String descricao
) {
    public static Imovel toImovel(ImovelDtoCadastroParam dto, String fileName) {

        return new Imovel(TipoImovel.valueOf(dto.tipoImovel), dto.inputPesquisa, new BigDecimal(dto.preco),
                new BigDecimal(dto.quartos), new BigDecimal(dto.banheiros) ,new BigDecimal(dto.area),
                      dto.titulo, fileName, dto.detalhes, dto.descricao);

    }
}
