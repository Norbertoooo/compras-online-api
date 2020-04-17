package com.audora.comprasonline.api.services;

import com.audora.comprasonline.api.model.CategoriaProduto;
import com.audora.comprasonline.api.repository.CategoriaProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaProdutoService {

    private final CategoriaProdutoRepository categoriaRepository;

    public CategoriaProdutoService(CategoriaProdutoRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaProduto> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public CategoriaProduto salvarCategoria( CategoriaProduto categoriaProduto) {
        return categoriaRepository.save(categoriaProduto);
    }
}
