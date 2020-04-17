package com.audora.comprasonline.api.services;

import com.audora.comprasonline.api.model.Usuario;
import com.audora.comprasonline.api.model.enums.PerfilEnum;
import com.audora.comprasonline.api.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario save(Usuario usuario) {
        // TODO: 16/04/2020 vereficar se usuario já existe, caso sim retornar badResquest
        if (usuario.getPerfil() == null)
            usuario.setPerfil(PerfilEnum.ROLE_USUARIO);

    // TODO: 16/04/2020 inserir encriptação da senha depois que adcionar o security

        return usuarioRepository.save(usuario);
    }

    public Usuario procurarUsuarioPorEmail( String email) {
        return usuarioRepository.findUsuarioByEmail(email);
    }

    public void excluirUsuario(String email) {
        log.info("Serviço - Excluindo usuario pelo email: {}", email);
        usuarioRepository.delete(procurarUsuarioPorEmail(email));
    }
}
