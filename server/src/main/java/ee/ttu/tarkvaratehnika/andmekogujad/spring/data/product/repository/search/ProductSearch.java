package ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.repository.search;

import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.model.Product;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.Collections;
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
            System.out.println("SEARCH INITIALIZATION FAILED");
            iE.printStackTrace();
        }
    }

    private List search(String query, String... fields) {
        em.getTransaction().begin();
        org.apache.lucene.search.Query luceneQuery = qb
                .keyword()
                .onFields(fields)
                .matching(query)
                .createQuery();
        javax.persistence.Query jpaQuery =
                ftem.createFullTextQuery(luceneQuery, Product.class);
        List result = jpaQuery.getResultList();
        em.getTransaction().commit();
        return result;
    }

    public List search(String query) {
        return search(query, "name", "ean", "producer", "origin");

    }

    public List searchByName(String query) {
        return search(query, "name");
    }

    public List searchByProducer(String query) {
        return search(query, "producer");
    }

    public List searchByOrigin(String query) {
        return search(query, "origin");
    }

    public List searchPages(String searchQuery, int page, int size, int dir) {

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

        if (dir == 1) {
            Collections.reverse(result);
        }

        int begin = page * size;

        List finalResult = new ArrayList();

        for (int i = begin; i < begin + size; i++) {
            if (result.size() - 1 >= i) {
                finalResult.add(result.get(i));
            } else {
                break;
            }
        }
        return finalResult;
    }


}
