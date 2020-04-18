package com.audora.comprasonline.api.security.service;

import com.audora.comprasonline.api.dto.UsuarioDto;
import com.audora.comprasonline.api.model.CarrinhoDeCompras;
import com.audora.comprasonline.api.model.Usuario;
import com.audora.comprasonline.api.model.enums.PerfilEnum;
import com.audora.comprasonline.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findUsuarioByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getSenha(),
                new ArrayList<>());
    }

    public Usuario save(UsuarioDto user) {
        Usuario newUser = new Usuario();
        newUser.setEmail(user.getEmail());
        newUser.setSenha(bcryptEncoder.encode(user.getSenha()));
        newUser.setPerfil(PerfilEnum.ROLE_USUARIO);
        CarrinhoDeCompras carrinhoDeCompras = new CarrinhoDeCompras();
        carrinhoDeCompras.setPrecoTotal(00.00);
        newUser.setCarrinhoDeCompras(carrinhoDeCompras);
        return usuarioRepository.save(newUser);
    }
}
