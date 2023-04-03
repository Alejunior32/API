package com.gft.Desafio_noticias_rapidas.repositories;

import com.gft.Desafio_noticias_rapidas.entities.Historico;
import com.gft.Desafio_noticias_rapidas.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico,Long> {
    List<Historico> findAllByUsuario(Usuario usuario);

    List<Historico> findAllByUsuario_Id(Long id);
}
