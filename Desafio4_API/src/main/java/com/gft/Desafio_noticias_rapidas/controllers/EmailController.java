package com.gft.Desafio_noticias_rapidas.controllers;

import com.gft.Desafio_noticias_rapidas.entities.Etiqueta;
import com.gft.Desafio_noticias_rapidas.entities.Usuario;
import com.gft.Desafio_noticias_rapidas.services.EmailSenderService;
import com.gft.Desafio_noticias_rapidas.services.EtiquetaService;
import com.gft.Desafio_noticias_rapidas.services.NoticiasService;
import com.gft.Desafio_noticias_rapidas.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/mail/enviar")
public class EmailController {

    private final NoticiasService noticiasService;

    private final EmailSenderService emailSenderService;

    private final UsuarioService usuarioService;

    private final EtiquetaService etiquetaService;

    public EmailController(NoticiasService noticiasService, EmailSenderService emailSenderService, UsuarioService usuarioService, EtiquetaService etiquetaService) {
        this.noticiasService = noticiasService;
        this.emailSenderService = emailSenderService;
        this.usuarioService = usuarioService;
        this.etiquetaService = etiquetaService;
    }



    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> enviarEmail(@RequestParam Long usuarioId){
        Usuario usuario = usuarioService.buscarUsuarioPeloPerfil(usuarioId);

        List<Etiqueta> etiquetas = etiquetaService.buscarEtiquetasDoUsuarioLista(usuario);
        List<String> nomeEtiquetas = new ArrayList<>();
        for (Etiqueta etiqueta : etiquetas){
            nomeEtiquetas.add(etiqueta.getNomeEtiqueta());
            etiquetaService.buscarEtiquetaDoUsuario(usuarioId,etiqueta);
        }

        emailSenderService.EnviarEmail(usuario.getEmail(), "Notícias diária",noticiasService.noticiasDataAtual(nomeEtiquetas).toString());
        return ResponseEntity.ok().build();
    }

}
