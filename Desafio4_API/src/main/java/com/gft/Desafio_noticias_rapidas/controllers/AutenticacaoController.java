package com.gft.Desafio_noticias_rapidas.controllers;

import com.gft.Desafio_noticias_rapidas.dto.Autenticacao.AutenticacaoDTO;
import com.gft.Desafio_noticias_rapidas.dto.Autenticacao.TokenDTO;
import com.gft.Desafio_noticias_rapidas.services.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.AuthenticationException;

@RestController
@RequestMapping("/v1/auth")
public class AutenticacaoController {

    @Autowired
    AutenticacaoService autenticacaoService;

    @PostMapping
    public ResponseEntity<TokenDTO> autenticar(@RequestBody AutenticacaoDTO authDTO){

        try{
            return ResponseEntity.ok(autenticacaoService.autenticar(authDTO));
        }catch (AuthenticationException exception){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }


    }

}
