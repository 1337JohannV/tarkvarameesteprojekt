package com.tarkvaramehed.projekt.tarkvarameesteprojekt.dao;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Primary
@Repository
public class JpaProductDao implements ProductDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Product> getProductsByCategory() {
        return null;
    }

    @Override
    public Product getById(Long id) {
        return em.find(Product.class, id);
    }

    @Override
    public List<Product> getAll() {
        return em.createQuery("select p from Product p", Product.class).getResultList();
    }

    @Transactional
    public void insert(Product product) {
        System.out.println(product);
        if (product.getId() == null) {
            em.persist(product);
        } else {
            em.merge(product);
        }
    }

    public static void main(String[] args) {
    }
}
