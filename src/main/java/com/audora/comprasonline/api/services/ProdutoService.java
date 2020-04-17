package com.audora.comprasonline.api.services;

import com.audora.comprasonline.api.model.Produto;
import com.audora.comprasonline.api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }


    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }
}
