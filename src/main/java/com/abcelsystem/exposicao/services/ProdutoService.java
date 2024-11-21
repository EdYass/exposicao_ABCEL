package com.abcelsystem.exposicao.services;

import com.abcelsystem.exposicao.dtos.ProdutoDTO;
import com.abcelsystem.exposicao.entities.Produto;
import com.abcelsystem.exposicao.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public List<ProdutoDTO> findAll() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(produto -> new ProdutoDTO(produto.getId(), produto.getNome(), produto.getVariedade()))
                .collect(Collectors.toList());
    }

    public Optional<ProdutoDTO> findById(UUID id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.map(p -> new ProdutoDTO(p.getId(), p.getNome(), p.getVariedade()));
    }

    public ProdutoDTO save(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        BeanUtils.copyProperties(produtoDTO, produto);
        Produto savedProduto = produtoRepository.save(produto);
        return new ProdutoDTO(savedProduto.getId(), savedProduto.getNome(), savedProduto.getVariedade());
    }

    public void deleteById(UUID id) {
        produtoRepository.deleteById(id);
    }
}
