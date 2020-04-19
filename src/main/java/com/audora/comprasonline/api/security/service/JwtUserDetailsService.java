package com.audora.comprasonline.api.security.service;

import com.audora.comprasonline.api.dto.UsuarioDto;
import com.audora.comprasonline.api.model.CarrinhoDeCompras;
import com.audora.comprasonline.api.model.Usuario;
import com.audora.comprasonline.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findUsuarioByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        List<GrantedAuthority> aurotizacaoAdmin = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USUARIO");
        List<GrantedAuthority> autorizacaoUsuario = AuthorityUtils.createAuthorityList( "ROLE_USUARIO");

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getSenha(),
                user.isAdmin()? aurotizacaoAdmin: autorizacaoUsuario);
    }

    public Usuario save(UsuarioDto user) {
        Usuario newUser = new Usuario();
        newUser.setEmail(user.getEmail());
        newUser.setSenha(passwordEncoder.encode(user.getSenha()));
        CarrinhoDeCompras carrinhoDeCompras = new CarrinhoDeCompras();
        carrinhoDeCompras.setPrecoTotal(00.00);
        newUser.setCarrinhoDeCompras(carrinhoDeCompras);
        return usuarioRepository.save(newUser);
    }
}
