package com.audora.comprasonline.api.repository;

import com.audora.comprasonline.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Usuario findUsuarioByEmail(String email);
}
