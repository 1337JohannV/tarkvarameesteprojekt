package com.tarkvaramehed.projekt.tarkvarameesteprojekt.data;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {

    @Query("select p from Product p where p.category=:category")
    Iterable<Product> findProductByCategory(@Param("category") Category category);

    List<Product> findAllByCategory(Category category, Pageable pageable);
}
