package com.compasso.loja;

import com.compasso.loja.dao.ProdutoDao;
import com.compasso.loja.modelo.Produto;
import com.compasso.loja.util.JPAutil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        /*Produto celular = new Produto();
        celular.setNome("Xiaomi Redmi");
        celular.setDescricao("Muito legal");
        celular.setPreco(new BigDecimal("800"));
*/


        while (true) {
            EntityManager em = JPAutil.getEntityManager();
            ProdutoDao dao = new ProdutoDao(em);
            System.out.println("Escolha uma opção" +
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
                    Produto chocolate = new Produto("Barra de chocolate Milka", " Barra de chocolate Milka Oreo 100g", 10, new BigDecimal("16.90"));
                    cadastrar3Produtos(notebook, tablet, chocolate, em, dao);

                    break;
                case 2:
                    System.out.println("Atualizando o primeiro produto");
                    break;
                case 3:
                    System.out.println("Excluindo o segundo produto");
                    Produto produto = dao.buscarPorId(2l);
                    excluir(produto, em, dao);
                    break;
                case 0:
                    System.out.println("Terminando execução");
                    System.exit(0);
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
        em.close();

    }

    public static void cadastrar3Produtos(Produto produto1, Produto produto2, Produto produto3,
                                          EntityManager em, ProdutoDao dao) {
        em.getTransaction().begin();
        dao.cadastrar(produto1);
        dao.cadastrar(produto2);
        dao.cadastrar(produto3);
        em.getTransaction().commit();
        for (Produto x : dao.buscarTodos()) {
            System.out.println(x);
        }
        em.close();
    }

    public static void excluir(Produto produto, EntityManager em, ProdutoDao dao) {
        em.getTransaction().begin();
        dao.remover(produto);
        em.getTransaction().commit();
        for (Produto x : dao.buscarTodos()) {
            System.out.println(x);
        }
        em.close();

    }
}
