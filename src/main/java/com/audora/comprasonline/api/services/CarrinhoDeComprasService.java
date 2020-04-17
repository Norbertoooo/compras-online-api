package com.audora.comprasonline.api.services;

import com.audora.comprasonline.api.repository.CarrinhoDeComprasRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CarrinhoDeComprasService {

    private static final Logger log = LoggerFactory.getLogger(CarrinhoDeComprasService.class);

    private final CarrinhoDeComprasRepository carrinhoDeComprasRepository;

    public CarrinhoDeComprasService(CarrinhoDeComprasRepository carrinhoDeComprasRepository) {
        this.carrinhoDeComprasRepository = carrinhoDeComprasRepository;
    }
    public void atualizaValorTotal( Float valor) {

    }
}
