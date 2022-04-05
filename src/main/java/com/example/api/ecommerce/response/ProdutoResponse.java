package com.example.api.ecommerce.response;

import com.example.api.ecommerce.model.Produto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class ProdutoResponse implements Serializable {
    private final Long id;
    private final String nome;
    private final BigDecimal preco;
    private final String imagem;

    public ProdutoResponse(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.imagem = produto.getImagem();
    }

    public Long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoResponse entity = (ProdutoResponse) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.nome, entity.nome) &&
                Objects.equals(this.preco, entity.preco) &&
                Objects.equals(this.imagem, entity.imagem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, preco, imagem);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "nome = " + nome + ", " +
                "preco = " + preco + ", " +
                "imagem = " + imagem + ")";
    }
}
