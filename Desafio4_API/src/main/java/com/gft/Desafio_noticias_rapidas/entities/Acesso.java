package com.gft.Desafio_noticias_rapidas.entities;


import javax.persistence.*;

@Entity
public class Acesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Etiqueta etiqueta;

    private Integer acessos;

//    private String dataRequisicao;

    public Acesso() {
    }

    public Acesso(Long id, Usuario usuario, Etiqueta etiqueta, Integer acessos) {
        this.id = id;
        this.usuario = usuario;
        this.etiqueta = etiqueta;
        this.acessos = acessos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Etiqueta getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(Etiqueta etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Integer getAcessos() {
        return acessos;
    }

    public void setAcessos(Integer acessos) {
        this.acessos = acessos;
    }
}
