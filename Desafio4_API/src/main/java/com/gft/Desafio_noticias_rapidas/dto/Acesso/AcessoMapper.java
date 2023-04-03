package com.gft.Desafio_noticias_rapidas.dto.Acesso;

import com.gft.Desafio_noticias_rapidas.entities.Acesso;

public class AcessoMapper {

    public static ConsultaAcessosDTO fromEntityToMaisAcessados(Acesso acesso){
        return new ConsultaAcessosDTO(acesso.getEtiqueta().getNomeEtiqueta(), acesso.getAcessos());
    }


}
