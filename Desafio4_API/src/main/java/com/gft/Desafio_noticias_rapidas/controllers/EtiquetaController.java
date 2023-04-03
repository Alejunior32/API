package com.gft.Desafio_noticias_rapidas.controllers;


import com.gft.Desafio_noticias_rapidas.dto.Etiqueta.ConsultaEtiquetaDTO;
import com.gft.Desafio_noticias_rapidas.dto.Etiqueta.ConsultaEtiquetaSemUsuariosDTO;
import com.gft.Desafio_noticias_rapidas.dto.Etiqueta.EtiquetaMapper;
import com.gft.Desafio_noticias_rapidas.dto.Etiqueta.RegistroEtiquetaDTO;
import com.gft.Desafio_noticias_rapidas.entities.Etiqueta;
import com.gft.Desafio_noticias_rapidas.entities.Usuario;
import com.gft.Desafio_noticias_rapidas.services.AutenticacaoService;
import com.gft.Desafio_noticias_rapidas.services.EtiquetaService;
import com.gft.Desafio_noticias_rapidas.services.UsuarioService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/v1/etiquetas")
public class EtiquetaController {

    private final EtiquetaService etiquetaService;

    @Lazy
    private final UsuarioService usuarioService;

    private final AutenticacaoService autenticacaoService;

    public EtiquetaController(EtiquetaService etiquetaService, UsuarioService usuarioService, AutenticacaoService autenticacaoService) {
        this.etiquetaService = etiquetaService;
        this.usuarioService = usuarioService;
        this.autenticacaoService = autenticacaoService;
    }

    @PostMapping("/usuario")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ConsultaEtiquetaSemUsuariosDTO> salvarEtiquetaParaUsuario(HttpServletRequest request, @RequestBody RegistroEtiquetaDTO etiquetaDTO){
        Etiqueta etiqueta = etiquetaService.verificarExistenciaEtiqueta(EtiquetaMapper.fromDTO(etiquetaDTO));
        usuarioService.salvarEtiqueta(autenticacaoService.retornarIdUsuario(autenticacaoService.getToken(request)),etiqueta);
        return ResponseEntity.ok(EtiquetaMapper.fromEntityToEtiquetaSemUsuario(etiqueta));
    }


    @GetMapping("/usuario")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Page<ConsultaEtiquetaSemUsuariosDTO>> buscarEtiquetasDoUsuario(HttpServletRequest request, @PageableDefault Pageable pageable){
        Usuario usuario = usuarioService.buscarUsuarioPeloPerfil(autenticacaoService.retornarIdUsuario(autenticacaoService.getToken(request)));
        return ResponseEntity.ok(etiquetaService.buscarEtiquetasDoUsuario(usuario,pageable).map(EtiquetaMapper::fromEntityToEtiquetaSemUsuario));
    }


    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Page<ConsultaEtiquetaDTO>> buscarEtiquetasPeloAdmin(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(etiquetaService.buscarTodasEtiquetas(pageable).map(EtiquetaMapper::fromEntityToConsultaDTO));
    }

    @GetMapping("/admin/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ConsultaEtiquetaDTO> buscarEtiquetaPeloAdmin(@PathVariable Long id){
        Etiqueta etiqueta =etiquetaService.buscarEtiqueta(id);
        return ResponseEntity.ok(EtiquetaMapper.fromEntityToConsultaDTO(etiqueta));
    }



}
