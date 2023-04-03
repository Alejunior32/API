package com.gft.Desafio_noticias_rapidas.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Etiqueta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nomeEtiqueta;


    @ManyToMany(mappedBy="etiquetas", cascade = CascadeType.ALL)
    private List<Usuario> usuarios;


    public Etiqueta() {
    }

    public Etiqueta(Long id, String nomeEtiqueta, List<Usuario> usuarios) {
        this.id = id;
        this.nomeEtiqueta = nomeEtiqueta;
        this.usuarios = usuarios;
    }

    public Etiqueta(Long id, String nomeEtiqueta) {
        this.id = id;
        this.nomeEtiqueta = nomeEtiqueta;
    }

    public Etiqueta(Long id) {
        this.id = id;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getNomeEtiqueta() {
        return nomeEtiqueta;
    }

    public void setNomeEtiqueta(String nomeEtiqueta) {
        this.nomeEtiqueta = nomeEtiqueta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
