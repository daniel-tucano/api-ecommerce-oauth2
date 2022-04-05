package com.example.api.ecommerce.controller;

import com.example.api.ecommerce.model.Produto;
import com.example.api.ecommerce.repository.ProdutoRepository;
import com.example.api.ecommerce.request.ProdutoRequest;
import com.example.api.ecommerce.response.ProdutoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/{id}")
    private ResponseEntity<ProdutoResponse> obtemProduto(@PathVariable Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        return ResponseEntity.ok(new ProdutoResponse(produto));
    }

    @PostMapping
    private ResponseEntity<ProdutoResponse> adicionaProdutor(@RequestBody ProdutoRequest request) {
        Produto produto = produtoRepository.save(request.toProduto());

        return ResponseEntity.status(HttpStatus.CREATED).body(new ProdutoResponse(produto));
    }

}
