package com.gft.Desafio_noticias_rapidas.repositories;

import com.gft.Desafio_noticias_rapidas.entities.Etiqueta;
import com.gft.Desafio_noticias_rapidas.entities.Acesso;
import com.gft.Desafio_noticias_rapidas.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, Long> {

    Optional<Acesso> findByUsuarioAndEtiqueta(Usuario usuario, Etiqueta etiqueta);

    List<Acesso> findAllByEtiqueta(Etiqueta etiqueta);

}
