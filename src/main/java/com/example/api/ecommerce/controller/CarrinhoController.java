package com.example.api.ecommerce.controller;

import com.example.api.ecommerce.repository.CarrinhoRepository;
import com.example.api.ecommerce.model.Carrinho;
import com.example.api.ecommerce.model.Produto;
import com.example.api.ecommerce.model.Usuario;
import com.example.api.ecommerce.repository.ProdutoRepository;
import com.example.api.ecommerce.repository.UsuarioRepository;
import com.example.api.ecommerce.response.CarrinhoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    private final CarrinhoRepository carrinhoRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;

    public CarrinhoController(CarrinhoRepository carrinhoRepository,
                              ProdutoRepository produtoRepository,
                              UsuarioRepository usuarioRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/{email}")
    private CarrinhoResponse obtemCarrinho(@PathVariable String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuario com o email fornecido não encontrado"));

        return new CarrinhoResponse(carrinhoRepository.findByUsuario(usuario));
    }

    @PostMapping("/{email}/adiciona/{idProduto}")
    private ResponseEntity<CarrinhoResponse> adicionaItemNoCarrinho(@PathVariable("email") String email, @PathVariable("idProduto") Long idProduto) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuario com o email fornecido não encontrado"));

        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new EntityNotFoundException("Produto com o id fornecido não encontrado"));

        Carrinho carrinho = carrinhoRepository.findByUsuario(usuario);

        carrinho.aumentaQuantidadeOuAdicionaProduto(produto);

        carrinhoRepository.save(carrinho);

        return ResponseEntity.ok(new CarrinhoResponse(carrinho));
    }

    @PostMapping("/{email}/remove/{idProduto}")
    private ResponseEntity<CarrinhoResponse> removeItemDoCarrinho(@PathVariable("email") String email, @PathVariable("idProduto") Long idProduto) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuario com o email fornecido não encontrado"));

        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new EntityNotFoundException("Produto com o id fornecido não encontrado"));

        Carrinho carrinho = carrinhoRepository.findByUsuario(usuario);

        carrinho.diminuiQuantidadeOuRemoveProduto(produto);

        carrinhoRepository.save(carrinho);

        return ResponseEntity.ok(new CarrinhoResponse(carrinho));
    }

}
