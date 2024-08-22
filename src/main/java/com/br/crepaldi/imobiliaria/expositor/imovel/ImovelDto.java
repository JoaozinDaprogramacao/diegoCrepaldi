package com.br.crepaldi.imobiliaria.expositor.imovel;

import java.math.BigDecimal;

public record ImovelDto(
        TipoImovel tipoImovel,
        String inputPesquisa,
        BigDecimal valormin,
        BigDecimal valormax,
        BigDecimal quartosmin,
        BigDecimal quartosmax,
        BigDecimal banheirosmin,
        BigDecimal banheirosmax,
        BigDecimal areamin,
        BigDecimal areamax,
        String titulo,
        String image
) {
}
