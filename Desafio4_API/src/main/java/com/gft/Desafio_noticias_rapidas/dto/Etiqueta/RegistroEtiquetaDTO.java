package com.gft.Desafio_noticias_rapidas.dto.Etiqueta;

public class RegistroEtiquetaDTO {

    private String nomeEtiqueta;

    public RegistroEtiquetaDTO(String nomeEtiqueta) {
        this.nomeEtiqueta = nomeEtiqueta;
    }

    public RegistroEtiquetaDTO() {
    }

    public String getNomeEtiqueta() {
        return nomeEtiqueta;
    }

    public void setNomeEtiqueta(String nomeEtiqueta) {
        this.nomeEtiqueta = nomeEtiqueta;
    }
}
