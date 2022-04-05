package com.example.api.ecommerce.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carrinho")
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(mappedBy = "carrinho", optional = false, orphanRemoval = true)
    private Usuario usuario;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCarrinho> itensCarrinho = new ArrayList<>();

    public Carrinho(List<ItemCarrinho> itensCarrinho) {
        this.itensCarrinho = itensCarrinho;
    }

    public Carrinho() {
    }

    public Boolean temProduto(Produto produto) {
        return itensCarrinho.stream().anyMatch(itemCarrinho -> itemCarrinho.getProduto().equals(produto));
    }

    private Boolean isUltimoProduto(Produto produto) {
        return temProduto(produto) ? obtemItemComProduto(produto).getQuantidade() == 1 : null;
    }

    public ItemCarrinho obtemItemComProduto(Produto produto) {
        return itensCarrinho.stream().filter(itemCarrinho -> itemCarrinho.getProduto().equals(produto)).findFirst().orElse(null);
    }

    public void aumentaQuantidadeOuAdicionaProduto(Produto produto) {
        if (!temProduto(produto)) {
            itensCarrinho.add(new ItemCarrinho(this, produto));
        } else {
            obtemItemComProduto(produto).aumentaQuantidade();
        }
    }

    public void diminuiQuantidadeOuRemoveProduto(Produto produto) {
        Boolean ehUltimoProduto = isUltimoProduto(produto);
        if (Boolean.TRUE.equals(ehUltimoProduto)) {
            itensCarrinho.remove(new ItemCarrinho(this, produto));
        } else {
            obtemItemComProduto(produto).diminuiQuantidade();
        }
    }

    public List<ItemCarrinho> getItensCarrinho() {
        return itensCarrinho;
    }

    public void setItensCarrinho(List<ItemCarrinho> itensCarrinho) {
        this.itensCarrinho = itensCarrinho;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}