package com.compasso.loja.dao;

import com.compasso.loja.modelo.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoDao {

    private EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto) {
        this.em.persist(produto);

    }

    public void atualizar(Produto produto) {
        this.em.merge(produto);
        produto.setDataAlteracao();
    }

    public void remover(Produto produto) {
        produto = em.merge(produto);
        this.em.remove(produto);
    }

    public List<Produto> buscarTodos() {
        String jpql = "SELECT p FROM Produto p ";
        return em.createQuery(jpql, Produto.class).getResultList();
    }

    //busca o produto na posição n do banco de dados
    public Produto buscarN(int n) {
        Produto resultado = null;
        try {
            String jpql = "SELECT p FROM Produto p ";
            resultado = em.createQuery(jpql, Produto.class).setMaxResults(1).setFirstResult(n - 1).getSingleResult();
            System.out.println(resultado);
        } catch (Exception e) {
            System.out.println("Registro não encontrado");
            System.out.println(e.getMessage());
        }
        return resultado;


    }


    public List<Produto> buscarPorNome(String nome) {
        String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
        return em.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList();
    }

    //busca a ultima ocorrência de um determinado produto no banco de dados
    public Produto buscarUltimo(String nome) {
        try {
            List<Produto> resultado = buscarPorNome(nome);
            return resultado.get(resultado.size() - 1);
        } catch (Exception e) {
            System.out.println("Este produto não está cadastrado");
            return null;
        }

    }


}
