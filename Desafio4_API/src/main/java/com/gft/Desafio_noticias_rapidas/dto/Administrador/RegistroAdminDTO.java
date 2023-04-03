package com.gft.Desafio_noticias_rapidas.dto.Administrador;

public class RegistroAdminDTO {

    private String email;

    private String senha;

    public RegistroAdminDTO() {
    }

    public RegistroAdminDTO(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
