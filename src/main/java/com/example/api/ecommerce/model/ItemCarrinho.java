package com.example.api.ecommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "item_carrinho")
public class ItemCarrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "carrinho_id", nullable = false)
    private Carrinho carrinho;

    public ItemCarrinho(Carrinho carrinho, Produto produto) {
        this.quantidade = 1;
        this.carrinho = carrinho;
        this.produto = produto;
    }

    public ItemCarrinho() {
    }

    public void aumentaQuantidade() {
        this.quantidade += 1;
    }

    public void diminuiQuantidade() {
        this.quantidade -= 1;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemCarrinho that = (ItemCarrinho) o;

        if (!produto.equals(that.produto)) return false;
        return carrinho.equals(that.carrinho);
    }

    @Override
    public int hashCode() {
        int result = produto.hashCode();
        result = 31 * result + carrinho.hashCode();
        return result;
    }
}