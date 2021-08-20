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

    public Produto buscarPorId(Long id) {
        return em.find(Produto.class, id);
    }

    public List<Produto> buscarTodos() {
        String jpql = "SELECT p FROM Produto p ";
        return em.createQuery(jpql, Produto.class).getResultList();
    }

    public Produto buscarN(int n) {
        Produto resultado = null;
        try {
            String jpql = "SELECT p FROM Produto p ";
            resultado = em.createQuery(jpql, Produto.class).setMaxResults(1).setFirstResult(n - 1).getSingleResult();
            System.out.println(resultado);
        } catch (Exception e) {
            System.out.println("Registro não encontrado");
            System.out.println(e.getMessage());

        }finally {
            return resultado;
        }

    }


    public List<Produto> buscarPorNome(String nome) {
        String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
        return em.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList();
    }


}
