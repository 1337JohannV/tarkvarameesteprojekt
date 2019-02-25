package com.tarkvaramehed.projekt.tarkvarameesteprojekt.data;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
