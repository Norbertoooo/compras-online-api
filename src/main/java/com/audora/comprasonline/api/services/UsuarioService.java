package com.audora.comprasonline.api.services;

import com.audora.comprasonline.api.model.CarrinhoDeCompras;
import com.audora.comprasonline.api.model.Usuario;
import com.audora.comprasonline.api.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario save(Usuario usuario) {

        usuario.setEmail(passwordEncoder.encode(usuario.getSenha()));

        if (usuario.getCarrinhoDeCompras() == null) {
            CarrinhoDeCompras carrinhoDeCompras = new CarrinhoDeCompras();
            usuario.setCarrinhoDeCompras(carrinhoDeCompras);
        }
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
