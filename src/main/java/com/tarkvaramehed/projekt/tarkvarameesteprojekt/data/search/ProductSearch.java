package com.tarkvaramehed.projekt.tarkvarameesteprojekt.data.search;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductSearch {

    @Autowired
    private EntityManagerFactory emf;

    private EntityManager em;
    private FullTextEntityManager ftem;
    private QueryBuilder qb;

    public void init() {
        try {
            em = emf.createEntityManager();
            ftem = org.hibernate.search.jpa.Search
                    .getFullTextEntityManager(em);
            ftem.createIndexer().startAndWait();
            qb = ftem.getSearchFactory()
                    .buildQueryBuilder()
                    .forEntity(Product.class).get();
        } catch (InterruptedException iE) {
            System.out.println("SEARCH INIT FAILED");
            iE.printStackTrace();
        }
    }

    public List search(String searchQuery) {
        em.getTransaction().begin();
        org.apache.lucene.search.Query luceneQuery = qb
                .keyword()
                .onFields("name", "ean", "producer", "origin")
                .matching(searchQuery)
                .createQuery();
        javax.persistence.Query jpaQuery =
                ftem.createFullTextQuery(luceneQuery, Product.class);
        List result = jpaQuery.getResultList();
        em.getTransaction().commit();
        return result;
    }

}
