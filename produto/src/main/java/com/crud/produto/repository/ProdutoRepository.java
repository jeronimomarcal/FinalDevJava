package com.crud.produto.repository;

import org.springframework.data.repository.CrudRepository;

import com.crud.produto.models.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, String> {
    Produto findByCodigoProduto(Long codigoProduto);
    Produto deleteByCodigoProduto(Long codigoProduto);
}