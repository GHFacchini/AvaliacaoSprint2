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
                    Produto notebook = new Produto("Notebook Dell", "Notebook Inspiron 15 3000", 3, new BigDecimal("2851"));
                    Produto tablet = new Produto("Tablet Samsung", "Samsung Galaxy Tab S6 Lite", 2, new BigDecimal("2218"));
                    Produto chocolate = new Produto("Barra de chocolate Milka", "Barra de chocolate Milka Oreo 100g", 10, new BigDecimal("16.90"));
                    cadastrar3Produtos(notebook, tablet, chocolate, em, dao);

                    fecharConexao(em);
                    break;
                case 2:
                    System.out.println("Atualizando o primeiro produto");
                    atualizarUltimo("Notebook Dell", em, dao);
                    fecharConexao(em);
                    break;
                case 3:
                    System.out.println("Excluindo o segundo produto");
                    excluirUltimo("Tablet Samsung", em, dao);

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

    //pode ser utilizado para cadastrar um produto
    public static void cadastrar(Produto produto, EntityManager em, ProdutoDao dao) {
        try {
            em.getTransaction().begin();
            dao.cadastrar(produto);
            em.getTransaction().commit();
            System.out.println("O produto:" + produto.getNome() + " foi cadastrado com sucesso" +
                    "\n" + produto);
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }


    }

    //cadastra automaticamente 3 produtos caso ocorra algum problema em algum deles efetua um rollback
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

    //exclui a última inserção do segundo produto cadastrado na primeira opção
    public static void excluirUltimo(String nome, EntityManager em, ProdutoDao dao) {
        em.getTransaction().begin();
        Produto referenciaProduto = dao.buscarUltimo(nome);
        if (referenciaProduto != null) {
            dao.remover(referenciaProduto);
            em.getTransaction().commit();
            System.out.println("O Produto: " + referenciaProduto.getNome() +
                    "\ncom o ID: " + referenciaProduto.getId() +
                    "\nfoi excluido");
        }

    }

    //atualiza a última inserção do primeiro produto cadastrado primeiro opção
    public static void atualizarUltimo(String nome, EntityManager em, ProdutoDao dao) {
        em.getTransaction().begin();
        Produto referenciaProduto = dao.buscarUltimo(nome);
        if (referenciaProduto != null) {
            referenciaProduto.setQuantidade(referenciaProduto.getQuantidade() - 1); //remove uma unidade do produto
            dao.atualizar(referenciaProduto);
            em.getTransaction().commit();
            System.out.println("O Produto: " + referenciaProduto.getNome() +
                    "\ncom o ID: " + referenciaProduto.getId() +
                    "\nfoi alterado");
        }

    }

    public static void fecharConexao(EntityManager em) {
        em.close();
    }

    //exclui o produto na posição n do banco de dados
    /*public static void excluirN(int n, EntityManager em, ProdutoDao dao) {
        Produto produto = buscarN(n, em, dao);
        if (produto != null) {
            dao.remover(produto);
            em.getTransaction().commit();
            System.out.println("O Produto: " + produto.getNome() +
                    "\ncom o ID: " + produto.getId() +
                    "\nfoi excluido");
        }

    }*/
    //busca o produto na posição n do banco de dados
    /*public static Produto buscarN(int n, EntityManager em, ProdutoDao dao) {
        em.getTransaction().begin();
        Produto produto = dao.buscarN(n);
        if (produto != null) {
            System.out.println(produto);
        }
        return produto;
    }*/


}

