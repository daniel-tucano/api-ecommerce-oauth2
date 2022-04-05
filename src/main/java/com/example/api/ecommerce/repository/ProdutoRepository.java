package com.example.api.ecommerce.repository;

import com.example.api.ecommerce.model.Produto;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long> {
}