package com.audora.comprasonline.api.controller;

import com.audora.comprasonline.api.model.CarrinhoDeCompras;
import com.audora.comprasonline.api.model.Produto;
import com.audora.comprasonline.api.services.CarrinhoDeComprasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrinho")
@CrossOrigin(origins = "*")
public class CarrinhoDeComprasController {

    private final CarrinhoDeComprasService carrinhoDeComprasService;

    public CarrinhoDeComprasController(CarrinhoDeComprasService carrinhoDeComprasService) {
        this.carrinhoDeComprasService = carrinhoDeComprasService;
    }

    @GetMapping("/{email}")
    public ResponseEntity<CarrinhoDeCompras> carrinhoPorEmail(@PathVariable String email) {
       CarrinhoDeCompras carrinhoDeCompras = carrinhoDeComprasService.encontrarCarrinhoPorEmail(email);
       return ResponseEntity.ok(carrinhoDeCompras);
    }

    @PutMapping("/produto/{email}")
    public ResponseEntity<CarrinhoDeCompras> adicionarProduto(@PathVariable String email, @RequestBody Produto produto) {
        CarrinhoDeCompras carrinhoDeCompras = carrinhoDeComprasService.adicionarProduto(email, produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(carrinhoDeComprasService.save(carrinhoDeCompras));
    }

    @PutMapping("/produtos/{email}")
    public ResponseEntity<CarrinhoDeCompras> adicionarProdutos(@PathVariable String email, @RequestBody List<Produto> produtos) {
        CarrinhoDeCompras carrinhoDeCompras = carrinhoDeComprasService.encontrarCarrinhoPorEmail(email);
        carrinhoDeCompras.getProdutos().addAll(produtos);
        return ResponseEntity.status(HttpStatus.CREATED).body(carrinhoDeComprasService.save(carrinhoDeCompras));
    }
}
