package com.compasso.loja.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String descricao;
    private Integer quantidade;
    private BigDecimal preco;
    private LocalDate dataCriacao;
    private LocalDate dataAlteracao;

    public Produto() {
    }

    public Produto(String nome, String descricao, Integer quantidade, BigDecimal preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.dataCriacao = LocalDate.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao() {
        this.dataAlteracao = LocalDate.now();
    }

    @Override
    public String toString() {
        return "===============================" +
                "\nInformações do produto:" +
                "\nID= " + id +
                "\nNome= |" + nome +
                "\nDescrição= " + descricao +
                "\nQuantidade= " + quantidade +
                "\nPreco= R$" + preco +
                "\nData de Criação= " + dataCriacao +
                "\nData de Alteração= " + dataAlteracao +
                "\n==============================="
                ;
    }
}
