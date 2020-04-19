package com.audora.comprasonline.api.controller;

import com.audora.comprasonline.api.model.Usuario;
import com.audora.comprasonline.api.services.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodosUsuarios() {
        log.info("Listando todos os usuarios no sistema");
        return ResponseEntity.ok().body(usuarioService.listarUsuarios());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Usuario> listarUsuarioPorEmail(@PathVariable String email) {
        log.info("Procurando usuario pelo email: {}", email);
        Usuario usuario = usuarioService.procurarUsuarioPorEmail(email);
        return ResponseEntity.ok().body(usuario);
    }

    @PutMapping
    public ResponseEntity<Usuario> atualizarUsuario(@Valid @RequestBody Usuario usuario) {
        log.info("Atualizando usuario: {}", usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    @DeleteMapping("/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> excluirUsuario( @PathVariable String email ) {
        log.info("Excluindo usuario do email: {}", email);
        usuarioService.excluirUsuario(email);
        return ResponseEntity.noContent().build();
    }
}
