package com.gft.Desafio_noticias_rapidas.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.gft.Desafio_noticias_rapidas.dto.Autenticacao.AutenticacaoDTO;
import com.gft.Desafio_noticias_rapidas.dto.Autenticacao.TokenDTO;
import com.gft.Desafio_noticias_rapidas.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class AutenticacaoService {

    @Autowired
    private AuthenticationManager authManager;

    @Value("${desafio.jwt.expiration}")
    private String expiration;
    @Value("${desafio.jwt.secret}")
    private String secret;
    @Value("${desafio.jwt.issuer}")
    private String issuer;

    public TokenDTO autenticar(AutenticacaoDTO authForm) throws AuthenticationException {

        Authentication authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(authForm.getEmail(), authForm.getSenha()));
        String token = gerarToken(authenticate);

        return new TokenDTO(token);

    }

    private Algorithm criarAlgoritmo() {
        return Algorithm.HMAC256(secret);
    }

    private String gerarToken(Authentication authenticate) {
        Usuario principal = (Usuario)authenticate.getPrincipal();

        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

        return JWT.create()
                .withIssuer(issuer)
                .withExpiresAt(dataExpiracao)
                .withSubject(principal.getId().toString())
                .sign(this.criarAlgoritmo());

    }

    public boolean verificaToken(String token) {

        try {
            if(token==null)
                return false;

            JWT.require(this.criarAlgoritmo()).withIssuer(issuer).build().verify(token);
            return true;

        }catch(JWTVerificationException exception) {
            return false;
        }

    }

    public Long retornarIdUsuario(String token) {
        String subject = JWT.require(this.criarAlgoritmo()).withIssuer(issuer).build().verify(token).getSubject();
        return Long.parseLong(subject);
    }

    public String getToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        String token = header.substring(7,header.length());

        return token;
    }

}
