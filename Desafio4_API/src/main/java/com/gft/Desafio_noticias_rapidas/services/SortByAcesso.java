package com.gft.Desafio_noticias_rapidas.services;

import com.gft.Desafio_noticias_rapidas.dto.Acesso.ConsultaAcessosDTO;

import java.util.Comparator;

public class SortByAcesso implements Comparator<ConsultaAcessosDTO> {

    @Override
    public int compare(ConsultaAcessosDTO o1, ConsultaAcessosDTO o2) {
        return o2.getAcessos() - o1.getAcessos();
    }
}
