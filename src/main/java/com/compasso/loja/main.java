package com.compasso.loja;

import com.compasso.loja.dao.ProdutoDao;
import com.compasso.loja.modelo.Produto;
import com.compasso.loja.util.JPAutil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        while (true) {
            EntityManager em = JPAutil.getEntityManager();
            ProdutoDao dao = new ProdutoDao(em);
            System.out.println("\nEscolha uma opção" +
                    "\n1 - Para cadastrar produtos" +
                    "\n2 - Para atualizar o primeiro produto" +
                    "\n3 - Para excluir o segundo produto" +
                    "\n0 - Para sair ");
            Scanner scanner = new Scanner(System.in);
            int valor = scanner.nextInt();
            switch (valor) {

                case 1:
                    System.out.println("Cadastrando produtos");
                    //String nome, String descricao, Integer quantidade, BigDecimal preco
                    Produto notebook = new Produto("Notebook Dell", "Notebook Inspiron 15 3000", 3, new BigDecimal("2851"));
                    Produto tablet = new Produto("Tablet Samsung", "Samsung Galaxy Tab S6 Lite", 2, new BigDecimal("2218"));
                    Produto chocolate = new Produto("Barra de chocolate Milka", "Barra de chocolate Milka Oreo 100g", 10, new BigDecimal("16.90"));
                    cadastrar3Produtos(notebook, tablet, chocolate, em, dao);

                    fecharConexao(em);
                    break;
                case 2:
                    System.out.println("Atualizando o primeiro produto");

                    fecharConexao(em);
                    break;
                case 3:
                    excluirN(2, em, dao);

                    fecharConexao(em);
                    break;
                case 0:
                    System.out.println("Terminando execução");
                    System.exit(0);

                    fecharConexao(em);
                    break;
                default:
                    System.out.println("Valor inválido");
                    break;
            }

        }


    }

    public static void cadastrar(Produto produto, EntityManager em, ProdutoDao dao) {
        em.getTransaction().begin();
        dao.cadastrar(produto);
        em.getTransaction().commit();
        for (Produto x : dao.buscarTodos()) {
            System.out.println(x);
        }

    }

    public static void cadastrar3Produtos(Produto produto1, Produto produto2, Produto produto3,
                                          EntityManager em, ProdutoDao dao) {
        try {
            em.getTransaction().begin();
            dao.cadastrar(produto1);
            dao.cadastrar(produto2);
            dao.cadastrar(produto3);
            em.getTransaction().commit();
            System.out.println("os seguites produtos foram cadastrados: " +
                    "\n" + produto1 + "\n" + produto2 + "\n" + produto3);
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public static void excluirN(int n, EntityManager em, ProdutoDao dao) {
        Produto produto = buscarN(n, em, dao);
        if (produto != null) {
            dao.remover(produto);
            em.getTransaction().commit();
            System.out.println("O Produto: " + produto.getNome() +
                    "\ncom o ID: " + produto.getId() +
                    "\nfoi excluido");
        }

    }

    public static Produto buscarN(int n, EntityManager em, ProdutoDao dao) {
        em.getTransaction().begin();
        Produto produto = dao.buscarN(n);
        if (produto != null) {
            System.out.println(produto);
        }
        return produto;
    }

    public static void fecharConexao(EntityManager em) {
        em.close();
    }

}

