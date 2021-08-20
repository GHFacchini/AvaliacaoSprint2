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


        while(true){
            EntityManager em = JPAutil.getEntityManager();
            ProdutoDao dao = new ProdutoDao(em);
            System.out.println("Escolha uma opção" +
                    "\n1 - Para cadastrar produtos" +
                    "\n2 - Para atualizar o primeiro produto" +
                    "\n3 - Para excluir o segundo produto" +
                    "\n0 - Para sair ");
            Scanner scanner = new Scanner(System.in);
            int valor = scanner.nextInt();
            switch (valor){

                case 1:
                    System.out.println("Cadastrando produtos");
                    //String nome, String descricao, Integer quantidade, BigDecimal preco
                    Produto notebook = new Produto("Notebook", "Notebook Inspiron 15 3000", 3, new BigDecimal("2851"));
                    cadastrar(notebook, em, dao);

                    break;
                case 2:
                    System.out.println("Atualizando o primeiro produto");
                    break;
                case 3:
                    System.out.println("Excluindo o segundo produto");
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

    public static void cadastrar(Produto produto, EntityManager em, ProdutoDao dao){
        em.getTransaction().begin();
        dao.cadastrar(produto);
        em.getTransaction().commit();
        for(Produto x: dao.buscarTodos()){
            System.out.println(x);
        }
        em.close();

    }
}
