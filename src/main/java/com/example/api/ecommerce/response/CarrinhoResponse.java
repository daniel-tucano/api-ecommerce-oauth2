package com.example.api.ecommerce.response;

import com.example.api.ecommerce.model.Carrinho;
import com.example.api.ecommerce.model.ItemCarrinho;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class CarrinhoResponse implements Serializable {
    private final UsuarioResponse usuario;
    private final Set<ItemCarrinhoResponse> itensCarrinho;

    public CarrinhoResponse(Carrinho carrinho) {
        this.usuario = new UsuarioResponse(carrinho.getUsuario());
        this.itensCarrinho = carrinho.getItensCarrinho().stream()
                .map(ItemCarrinhoResponse::new)
                .collect(Collectors.toSet());
    }

    public CarrinhoResponse(UsuarioResponse usuario, Set<ItemCarrinhoResponse> itensCarrinho) {
        this.usuario = usuario;
        this.itensCarrinho = itensCarrinho;
    }

    public UsuarioResponse getUsuario() {
        return usuario;
    }

    public Set<ItemCarrinhoResponse> getItensCarrinho() {
        return itensCarrinho;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarrinhoResponse entity = (CarrinhoResponse) o;
        return Objects.equals(this.usuario, entity.usuario) &&
                Objects.equals(this.itensCarrinho, entity.itensCarrinho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, itensCarrinho);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "usuario = " + usuario + ", " +
                "itensCarrinho = " + itensCarrinho + ")";
    }

    public static class ItemCarrinhoResponse implements Serializable {
        private final Integer quantidade;
        private final ProdutoResponse produto;

        public ItemCarrinhoResponse(ItemCarrinho itemCarrinho) {
            this.quantidade = itemCarrinho.getQuantidade();
            this.produto = new ProdutoResponse(itemCarrinho.getProduto());
        }

        public ItemCarrinhoResponse(Integer quantidade, ProdutoResponse produto) {
            this.quantidade = quantidade;
            this.produto = produto;
        }

        public Integer getQuantidade() {
            return quantidade;
        }

        public ProdutoResponse getProduto() {
            return produto;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ItemCarrinhoResponse entity = (ItemCarrinhoResponse) o;
            return Objects.equals(this.quantidade, entity.quantidade) &&
                    Objects.equals(this.produto, entity.produto);
        }

        @Override
        public int hashCode() {
            return Objects.hash(quantidade, produto);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "quantidade = " + quantidade + ", " +
                    "produto = " + produto + ")";
        }
    }
}
