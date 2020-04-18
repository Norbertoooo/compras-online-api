package com.audora.comprasonline.api.controller;

import com.audora.comprasonline.api.dto.ProdutoDto;
import com.audora.comprasonline.api.model.CarrinhoDeCompras;
import com.audora.comprasonline.api.model.Produto;
import com.audora.comprasonline.api.services.CarrinhoDeComprasService;
import com.audora.comprasonline.api.services.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrinho")
@CrossOrigin(origins = "*")
public class CarrinhoDeComprasController {

    private final CarrinhoDeComprasService carrinhoDeComprasService;

    private final ProdutoService produtoService;

    public CarrinhoDeComprasController(CarrinhoDeComprasService carrinhoDeComprasService, ProdutoService produtoService) {
        this.carrinhoDeComprasService = carrinhoDeComprasService;
        this.produtoService = produtoService;
    }

    @GetMapping("/{email}")
    public ResponseEntity<CarrinhoDeCompras> carrinhoPorEmail(@PathVariable String email) {
       CarrinhoDeCompras carrinhoDeCompras = carrinhoDeComprasService.encontrarCarrinhoPorEmail(email);
       return ResponseEntity.ok(carrinhoDeCompras);
    }

    @PostMapping("/{email}")
    public ResponseEntity<CarrinhoDeCompras> adicionarProduto(@PathVariable String email, @RequestBody ProdutoDto produtoDto) {
        CarrinhoDeCompras carrinhoDeCompras = carrinhoDeComprasService.encontrarCarrinhoPorEmail(email);
        Produto produto = produtoService.findByNome(produtoDto);
        carrinhoDeCompras.getProdutos().add(produto);
        carrinhoDeCompras.setPrecoTotal(carrinhoDeCompras.getPrecoTotal() + produto.getPreco());
        return ResponseEntity.status(HttpStatus.CREATED).body(carrinhoDeComprasService.save(carrinhoDeCompras));
    }

    @PostMapping("/produtos/{email}")
    public ResponseEntity<CarrinhoDeCompras> adicionarProdutos(@PathVariable String email, @RequestBody List<Produto> produtos) {
        CarrinhoDeCompras carrinhoDeCompras = carrinhoDeComprasService.encontrarCarrinhoPorEmail(email);
        carrinhoDeCompras.getProdutos().addAll(produtos);
        return ResponseEntity.status(HttpStatus.CREATED).body(carrinhoDeComprasService.save(carrinhoDeCompras));
    }
}
