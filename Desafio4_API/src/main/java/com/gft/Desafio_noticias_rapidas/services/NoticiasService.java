package com.gft.Desafio_noticias_rapidas.services;

import com.gft.Desafio_noticias_rapidas.dto.Noticia.ListaNoticiasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoticiasService {

    private final HistoricoService historicoService;

    public NoticiasService(HistoricoService historicoService) {
        this.historicoService = historicoService;
    }

    @Autowired
    private WebClient webClient;



    private String getDate() {
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataAtual.format(formatter);
    }

    public ListaNoticiasDTO noticiaComData(String etiqueta, String data){

        Mono<ListaNoticiasDTO> mono = this.webClient
                .method(HttpMethod.GET)
                .uri("/api/?q={etiqueta}&date={data}",etiqueta,data)
                .retrieve()
                .bodyToMono(ListaNoticiasDTO.class);

        ListaNoticiasDTO listaNoticiasDTO = mono.block();

        return listaNoticiasDTO;
    }

    public ListaNoticiasDTO noticiaDataAtual(String etiqueta){

        Mono<ListaNoticiasDTO> mono = this.webClient
                .method(HttpMethod.GET)
                .uri("/api/?q={etiqueta}&date={data}",etiqueta,getDate())
                .retrieve()
                .bodyToMono(ListaNoticiasDTO.class);

        ListaNoticiasDTO listaNoticiasDTO = mono.block();
        
        return listaNoticiasDTO;
    }



    public List<ListaNoticiasDTO> noticiasComData(List<String> etiquetas, String data){

        List<ListaNoticiasDTO> listaNoticiasDTOList = new ArrayList<>();

        for(String etiqueta: etiquetas){

            Mono<ListaNoticiasDTO> mono = this.webClient
                    .method(HttpMethod.GET)
                    .uri("/api/?q={etiqueta}&date={data}",etiqueta,data)
                    .retrieve()
                    .bodyToMono(ListaNoticiasDTO.class);

            listaNoticiasDTOList.add(mono.block());
        }
        return listaNoticiasDTOList;
    }

    public List<ListaNoticiasDTO> noticiasDataAtual(List<String> etiquetas){

        List<ListaNoticiasDTO> listaNoticiasDTOList = new ArrayList<>();

        for(String etiqueta: etiquetas){

            Mono<ListaNoticiasDTO> mono = this.webClient
                    .method(HttpMethod.GET)
                    .uri("/api/?q={etiqueta}&date={data}",etiqueta,getDate())
                    .retrieve()
                    .bodyToMono(ListaNoticiasDTO.class);

            listaNoticiasDTOList.add(mono.block());
        }
        return listaNoticiasDTOList;
    }
}
