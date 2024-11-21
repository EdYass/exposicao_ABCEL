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

    public ProdutoDTO save(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        BeanUtils.copyProperties(produtoDTO, produto);
        Produto savedProduto = produtoRepository.save(produto);
        return convertToDTO(savedProduto);
    }

    public List<ProdutoDTO> findAll() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(this::convertToDTO)  // Usa o método auxiliar para conversão
                .collect(Collectors.toList());
    }

    public Optional<ProdutoDTO> findById(UUID id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.map(this::convertToDTO);  // Usa o método auxiliar para conversão
    }

    public ProdutoDTO edit(UUID id, ProdutoDTO produtoDTO) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setNome(produtoDTO.nome());
        produto.setVariedade(produtoDTO.variedade());

        Produto updatedProduto = produtoRepository.save(produto);
        return convertToDTO(updatedProduto);
    }

    public void deleteById(UUID id) {
        produtoRepository.deleteById(id);
    }

    private ProdutoDTO convertToDTO(Produto produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getVariedade());
    }
}

