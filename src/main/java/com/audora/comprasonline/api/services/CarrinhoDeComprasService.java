package com.audora.comprasonline.api.services;

import com.audora.comprasonline.api.model.CarrinhoDeCompras;
import com.audora.comprasonline.api.model.Usuario;
import com.audora.comprasonline.api.repository.CarrinhoDeComprasRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoDeComprasService {

    private static final Logger log = LoggerFactory.getLogger(CarrinhoDeComprasService.class);

    private final CarrinhoDeComprasRepository carrinhoDeComprasRepository;

    private final UsuarioService usuarioService;

    public CarrinhoDeComprasService(CarrinhoDeComprasRepository carrinhoDeComprasRepository, UsuarioService usuarioService) {
        this.carrinhoDeComprasRepository = carrinhoDeComprasRepository;
        this.usuarioService = usuarioService;
    }

    public CarrinhoDeCompras encontrarCarrinhoPorEmail(String email) {
        Usuario usuario = usuarioService.procurarUsuarioPorEmail(email);
        Optional<CarrinhoDeCompras> carrinhoDeCompras = carrinhoDeComprasRepository.findById(usuario.getCarrinhoDeCompras().getId());
        return carrinhoDeCompras.get();
    }

    public CarrinhoDeCompras save(CarrinhoDeCompras carrinhoDeCompras) {
        return carrinhoDeComprasRepository.save(carrinhoDeCompras);
    }

    public List<CarrinhoDeCompras> finAll() {
        return carrinhoDeComprasRepository.findAll();
    }

}
