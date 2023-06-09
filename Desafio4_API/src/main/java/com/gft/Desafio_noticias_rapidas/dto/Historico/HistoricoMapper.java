package com.gft.Desafio_noticias_rapidas.dto.Historico;

import com.gft.Desafio_noticias_rapidas.entities.Historico;

public class HistoricoMapper {

    public static ConsultaHistoricoDTO fromEntity(Historico historico){
        return new ConsultaHistoricoDTO(historico.getEtiqueta().getNomeEtiqueta(), historico.getData());
    }
}
