package com.gft.Desafio_noticias_rapidas.dto.Administrador;

import com.gft.Desafio_noticias_rapidas.entities.Perfil;
import com.gft.Desafio_noticias_rapidas.entities.Usuario;

public class AdminMapper {


    public static Usuario fromDTO(RegistroAdminDTO dto){

        Perfil perfil = new Perfil();
        perfil.setId(1L);


        return new Usuario(null,dto.getEmail(), dto.getSenha(), perfil);
    }

    public static ConsultaAdminDTO fromEntity(Usuario administrador){
        return  new ConsultaAdminDTO(administrador.getId(), administrador.getEmail(), administrador.getSenha());
    }
}
