package com.tarkvaramehed.projekt.tarkvarameesteprojekt.dao;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public Product getById() {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }
}
