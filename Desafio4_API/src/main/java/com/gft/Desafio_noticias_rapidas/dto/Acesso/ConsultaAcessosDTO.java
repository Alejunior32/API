package com.gft.Desafio_noticias_rapidas.dto.Acesso;

public class ConsultaAcessosDTO {

    private String etiqueta;

    private Integer acessos;

    public ConsultaAcessosDTO() {
    }

    public ConsultaAcessosDTO(String etiqueta, Integer acessos) {
        this.etiqueta = etiqueta;
        this.acessos = acessos;
    }

    public ConsultaAcessosDTO(Integer acessos) {
        this.acessos=acessos;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Integer getAcessos() {
        return acessos;
    }

    public void setAcessos(Integer acessos) {
        this.acessos = acessos;
    }
}
