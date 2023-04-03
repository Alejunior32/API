package com.gft.Desafio_noticias_rapidas.dto.Noticia;

import java.util.List;

public class ListaNoticiasDTO {

    private Integer count;

    private List<NoticiaDTO> list;

    public ListaNoticiasDTO(Integer count, List<NoticiaDTO> list) {
        this.count = count;
        this.list = list;
    }

    public ListaNoticiasDTO() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<NoticiaDTO> getList() {
        return list;
    }

    public void setList(List<NoticiaDTO> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ListaNoticiasDTO{" +
                "count=" + count +'\''+
                ", list=" + list +'\''+
                '}';
    }
}
