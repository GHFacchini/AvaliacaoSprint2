package com.compasso.loja;

import com.compasso.loja.dao.ProdutoDao;
import com.compasso.loja.modelo.Produto;
import com.compasso.loja.util.JPAutil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class main {
    public static void main(String[] args) {
        Produto celular = new Produto();
        celular.setNome("Xiaomi Redmi");
        celular.setDescricao("Muito legal");
        celular.setPreco(new BigDecimal("800"));

        EntityManager em = JPAutil.getEntityManager();
        ProdutoDao dao = new ProdutoDao(em);

        em.getTransaction().begin();
        dao.cadastrar(celular);
        em.getTransaction().commit();
        em.close();
    }
}
