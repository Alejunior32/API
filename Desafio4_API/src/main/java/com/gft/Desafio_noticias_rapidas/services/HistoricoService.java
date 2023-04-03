package com.gft.Desafio_noticias_rapidas.services;

import com.gft.Desafio_noticias_rapidas.dto.Acesso.ConsultaAcessosDTO;
import com.gft.Desafio_noticias_rapidas.entities.Acesso;
import com.gft.Desafio_noticias_rapidas.entities.Etiqueta;
import com.gft.Desafio_noticias_rapidas.entities.Historico;
import com.gft.Desafio_noticias_rapidas.entities.Usuario;
import com.gft.Desafio_noticias_rapidas.repositories.AcessoRepository;
import com.gft.Desafio_noticias_rapidas.repositories.HistoricoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class HistoricoService {

    private final AcessoRepository acessoRepository;

    private final EtiquetaService etiquetaService;

    private final HistoricoRepository historicoRepository;

    public HistoricoService(AcessoRepository acessoRepository, EtiquetaService etiquetaService, HistoricoRepository historicoRepository) {
        this.acessoRepository = acessoRepository;
        this.etiquetaService = etiquetaService;
        this.historicoRepository = historicoRepository;
    }

    private String getDate() {
        LocalDateTime dataAtual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dataAtual.format(formatter);
    }

    public Acesso salvarAcesso(Usuario usuario, Etiqueta etiqueta){
        Optional<Acesso> op_acesso = acessoRepository.findByUsuarioAndEtiqueta(usuario,etiqueta);

        if (op_acesso.isPresent()){
            Acesso acesso = op_acesso.get();
            acesso.setAcessos(acesso.getAcessos()+1);
            return  acessoRepository.save(acesso);
        }

        Acesso acesso = new Acesso();
        acesso.setEtiqueta(etiqueta);
        acesso.setUsuario(usuario);
        acesso.setAcessos(1);
        return acessoRepository.save(acesso);
    }

    public Historico salvarHistorico(Usuario usuario, Etiqueta etiqueta){
        Historico historico= new Historico();
        historico.setUsuario(usuario);
        historico.setEtiqueta(etiqueta);
        historico.setData(getDate());
        return historicoRepository.save(historico);
    }

    public List<Historico> buscarHistoricoPeloUsuario(Long id){
        List<Historico> historicos = historicoRepository.findAllByUsuario_Id(id);
        Collections.sort(historicos, new SortByData());
        return historicos;
    }

    public List<ConsultaAcessosDTO> historicoEtiquetaMaisAcessado() {

        List<Etiqueta> etiquetas = etiquetaService.buscarTodasEtiquetasHistorico();

        List<Acesso> historicosEtiqueta;
        List<ConsultaAcessosDTO> consultaHistorico = new ArrayList<>();

        for (Etiqueta etiqueta:
             etiquetas) {
            Integer acesso = 0;
            historicosEtiqueta = acessoRepository.findAllByEtiqueta(etiqueta);

            for (int i=0;i<historicosEtiqueta.size();i++){
                acesso += historicosEtiqueta.get(i).getAcessos();
            }

            consultaHistorico.add(new ConsultaAcessosDTO(etiqueta.getNomeEtiqueta(),acesso));
        }

        Collections.sort(consultaHistorico, new SortByAcesso());

        List<ConsultaAcessosDTO> dezMaioresAcessos=new ArrayList<>();

        for (int i=0;i<10;i++){
            if (consultaHistorico.size() > i){
                dezMaioresAcessos.add(consultaHistorico.get(i));
            }
        }

        return dezMaioresAcessos;

    }


}
