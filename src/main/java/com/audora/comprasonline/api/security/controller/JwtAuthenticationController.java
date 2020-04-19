package com.audora.comprasonline.api.security.controller;

import com.audora.comprasonline.api.dto.UsuarioDto;
import com.audora.comprasonline.api.security.JwtTokenUtil;
import com.audora.comprasonline.api.security.model.JwtRequest;
import com.audora.comprasonline.api.security.model.JwtResponse;
import com.audora.comprasonline.api.security.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping("/autenticar")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getEmail(), authenticationRequest.getSenha());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("Usuario desabilitado", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Credenciais invalidas", e);
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> saveUser( @Valid @RequestBody UsuarioDto user) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(userDetailsService.save(user));
    }

}
