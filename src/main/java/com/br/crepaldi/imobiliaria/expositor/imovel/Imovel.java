package com.br.crepaldi.imobiliaria.expositor.imovel;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "imoveis")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private TipoImovel tipoImovel;
    private String inputPesquisa;
    private BigDecimal preco;
    private BigDecimal quartos;
    private BigDecimal banheiros;
    private BigDecimal area;
    private String titulo;
    @ElementCollection
    private List<String> imagens;
    private String imagem_capa;
    private String detalhes;
    private String descricao;

    public Imovel(TipoImovel tipoImovel, String inputPesquisa,
                  BigDecimal preco, BigDecimal quartos, BigDecimal banheiros, BigDecimal area, String titulo,
                  List<String> imagem, String imagem_capa, String detalhes, String descricao) {
        this.tipoImovel = tipoImovel;
        this.inputPesquisa = inputPesquisa;
        this.area = area;
        this.titulo = titulo;
        this.imagens = imagem;
        this.detalhes = detalhes;
        this.descricao = descricao;
        this.preco = preco;
        this.quartos = quartos;
        this.banheiros = banheiros;
        this.imagem_capa = imagem_capa;
    }
}
