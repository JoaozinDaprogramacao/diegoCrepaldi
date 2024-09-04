package com.br.crepaldi.imobiliaria.expositor.imovel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface ImovelRepository extends JpaRepository<Imovel, Integer> {
}