package com.example.api.ecommerce.repository;

import com.example.api.ecommerce.model.Carrinho;
import com.example.api.ecommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

    Carrinho findByUsuario(Usuario usuario);

}