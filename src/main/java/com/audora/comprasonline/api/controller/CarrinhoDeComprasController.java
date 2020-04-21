package com.audora.comprasonline.api.controller;

import com.audora.comprasonline.api.dto.ProdutoDto;
import com.audora.comprasonline.api.model.CarrinhoDeCompras;
import com.audora.comprasonline.api.model.Produto;
import com.audora.comprasonline.api.services.CarrinhoDeComprasService;
import com.audora.comprasonline.api.services.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
       return ResponseEntity.ok().body(carrinhoDeCompras);
    }

    @PostMapping("/{email}")
    public ResponseEntity<CarrinhoDeCompras> adicionarProduto(@PathVariable String email, @RequestBody ProdutoDto produtoDto) {
        CarrinhoDeCompras carrinhoDeCompras = carrinhoDeComprasService.encontrarCarrinhoPorEmail(email);
        Produto produto = produtoService.findByNome(produtoDto);
        carrinhoDeCompras.getProdutos().add(produto);
        carrinhoDeCompras.setPrecoTotal(carrinhoDeCompras.getPrecoTotal() + produto.getPreco());

        return ResponseEntity.status(HttpStatus.CREATED).body(carrinhoDeComprasService.save(carrinhoDeCompras));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<CarrinhoDeCompras> removerProduto(@PathVariable String email, @RequestBody ProdutoDto produtoDto) {
        CarrinhoDeCompras carrinhoDeCompras = carrinhoDeComprasService.encontrarCarrinhoPorEmail(email);
        Produto produto = produtoService.findByNome(produtoDto);
        carrinhoDeCompras.getProdutos().remove(produto);
        carrinhoDeCompras.setPrecoTotal(carrinhoDeCompras.getPrecoTotal() - produto.getPreco());
        carrinhoDeComprasService.save(carrinhoDeCompras);
        return ResponseEntity.noContent().build();
    }

}
