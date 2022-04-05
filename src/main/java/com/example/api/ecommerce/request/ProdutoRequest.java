package com.example.api.ecommerce.request;

import com.example.api.ecommerce.model.Produto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class ProdutoRequest implements Serializable {
    private final String nome;
    private final BigDecimal preco;
    private final String imagem;

    public ProdutoRequest(String nome, BigDecimal preco, String imagem) {
        this.nome = nome;
        this.preco = preco;
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getImagem() {
        return imagem;
    }

    public Produto toProduto() {
        return new Produto(nome,preco,imagem);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoRequest entity = (ProdutoRequest) o;
        return Objects.equals(this.nome, entity.nome) &&
                Objects.equals(this.preco, entity.preco) &&
                Objects.equals(this.imagem, entity.imagem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, preco, imagem);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "nome = " + nome + ", " +
                "preco = " + preco + ", " +
                "imagem = " + imagem + ")";
    }
}
