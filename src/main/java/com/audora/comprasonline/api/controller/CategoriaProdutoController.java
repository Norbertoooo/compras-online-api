package com.audora.comprasonline.api.controller;

import com.audora.comprasonline.api.model.CategoriaProduto;
import com.audora.comprasonline.api.services.CategoriaProdutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria-produto")
@CrossOrigin(origins = "*")
public class CategoriaProdutoController {

    private static final Logger log = LoggerFactory.getLogger(CategoriaProdutoController.class);

    private final CategoriaProdutoService CategoriaProdutoService;

    public CategoriaProdutoController(CategoriaProdutoService CategoriaProdutoService) {
        this.CategoriaProdutoService = CategoriaProdutoService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaProduto>> listarCategorias() {
        log.info("Request to find all Categorias ");
        return ResponseEntity.ok().body(CategoriaProdutoService.listarCategorias());
    }

    @PostMapping
    public ResponseEntity<CategoriaProduto> cadastrarCategoria(@RequestBody CategoriaProduto categoriaProduto) {
        log.info("Request to save categoria: {}", categoriaProduto);
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaProdutoService.salvarCategoria(categoriaProduto));
    }
}
