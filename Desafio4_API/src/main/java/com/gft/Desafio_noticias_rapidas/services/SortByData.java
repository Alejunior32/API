package com.gft.Desafio_noticias_rapidas.services;

import com.gft.Desafio_noticias_rapidas.entities.Historico;

import java.util.Comparator;

public class SortByData implements Comparator<Historico> {

    @Override
    public int compare(Historico o1, Historico o2) {
        return o2.getData().compareTo(o1.getData());
    }
}
