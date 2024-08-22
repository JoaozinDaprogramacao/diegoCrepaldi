package com.br.crepaldi.imobiliaria.expositor.imovel.cadastrar;

import com.br.crepaldi.imobiliaria.expositor.imovel.Imovel;
import com.br.crepaldi.imobiliaria.expositor.imovel.TipoImovel;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public record ImovelDtoCadastro(
        @NotBlank(message = "Digite o tipo do imóvel.")
        TipoImovel tipoImovel,

        @NotBlank(message = "Digite a localidade.")
        String inputPesquisa,

        @NotBlank(message = "Digite o preço.")
        BigDecimal preco,

        @NotBlank(message = "Digite a quantidade de quartos.")
        BigDecimal quartos,

        @NotBlank(message = "Digite a quantidade de banheiros")
        BigDecimal banheiros,

        @NotBlank(message = "Digite a área do imóvel.")
        BigDecimal area,

        @NotBlank(message = "De um titulo ao imóvel.")
        String titulo,

        @NotBlank(message = "Selecione uma imagem sobre o imóvel.")
        String imagem,

        @NotBlank(message = "Descreva o imóvel.")
        String detalhes,

        @NotBlank(message = "Crie uma descrição para o imóvel.")
        String descricao
) {
    public static Imovel toImovel(ImovelDtoCadastro dto) {
        return new Imovel(dto.tipoImovel, dto.inputPesquisa, dto.preco, dto.quartos,
                dto.banheiros,dto.area, dto.titulo, dto.imagem, dto.detalhes, dto.descricao);
    }
}
