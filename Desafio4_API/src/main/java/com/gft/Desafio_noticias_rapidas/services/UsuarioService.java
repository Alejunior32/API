package com.gft.Desafio_noticias_rapidas.services;

import com.gft.Desafio_noticias_rapidas.entities.Etiqueta;
import com.gft.Desafio_noticias_rapidas.entities.Usuario;
import com.gft.Desafio_noticias_rapidas.exceptions.AdminNaoPodeTerEtiquetaException;
import com.gft.Desafio_noticias_rapidas.exceptions.EntityNotFoundException;
import com.gft.Desafio_noticias_rapidas.exceptions.EtiquetaJaExisteException;
import com.gft.Desafio_noticias_rapidas.exceptions.UsuarioComEmailJaExisteException;
import com.gft.Desafio_noticias_rapidas.repositories.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    private final EtiquetaService etiquetaService;

    public UsuarioService(UsuarioRepository usuarioRepository, EtiquetaService etiquetaService) {
        this.usuarioRepository = usuarioRepository;
        this.etiquetaService = etiquetaService;
    }

    public Usuario salvarUsuario(Usuario usuario){
        if (!usuarioRepository.existsByEmail(usuario.getEmail())){
            return usuarioRepository.save(usuario);
        }
        throw new UsuarioComEmailJaExisteException("Já Existe um usuário com esse email");
    }


    public Usuario buscarUsuarioPeloPerfil(Long id){
        Optional<Usuario> op_usuario = usuarioRepository.findByIdAndPerfil_nome(id,"USUARIO");
        return op_usuario.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado") );
    }

    public Usuario buscarUsuarioPeloID(Long id){
        Optional<Usuario> op_usuario = usuarioRepository.findById(id);
        return op_usuario.orElseThrow(() -> new EntityNotFoundException("Não encontrado")  );
    }

    public Usuario buscarAdminPeloPerfil(Long id) {
        Optional<Usuario>  op_admin = usuarioRepository.findByIdAndPerfil_nome(id,"ADMIN");
        return op_admin.orElseThrow(() -> new EntityNotFoundException("Administrador não encontrado"));
    }



    public Page<Usuario> buscarTodosUsuarios(Pageable pageable){
        return usuarioRepository.findAllByPerfil_nome(pageable,"USUARIO");
    }

    public Page<Usuario> buscarTodosAdmins(Pageable pageable) {
        return usuarioRepository.findAllByPerfil_nome(pageable,"ADMIN");
    }

    public Usuario atualizarUsuario(Usuario usuario,Long id){
        Usuario usuarioInicial = this.buscarUsuarioPeloID(id);
        usuario.setId(usuarioInicial.getId());
        return usuarioRepository.save(usuario);
    }


    public Usuario salvarEtiqueta(Long id, Etiqueta etiqueta){

        Usuario usuario = buscarUsuarioPeloID(id);
        List<Etiqueta> etiquetas = usuario.getEtiquetas();

        if(usuario.getPerfil().getNome().equals("USUARIO")){
            if (etiquetas.contains(etiqueta)){
                throw new EtiquetaJaExisteException("Etiqueta já existe para esse usuário");
            }else{
                etiquetas.add(etiqueta);
                return atualizarUsuario(usuario,id);
            }
        }

        throw new AdminNaoPodeTerEtiquetaException("Não é possivel adicionar Etiqueta para Admin");

    }

    public void deletarUsuario(Long id) {
        Usuario usuario = this.buscarUsuarioPeloID(id);
        usuarioRepository.delete(usuario);
    }


    public Usuario buscarUsuarioPorEmail(String email){
        Optional<Usuario> op_usuario= usuarioRepository.findByEmail(email);

        if (op_usuario.isEmpty()){
            throw new RuntimeException("Usuário não encontrado");
        }
        return op_usuario.get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return buscarUsuarioPorEmail(username);
    }
}
