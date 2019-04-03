package com.tarkvaramehed.projekt.tarkvarameesteprojekt.data.repository;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Store;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>,
        PagingAndSortingRepository<Product, Long> {


    List<Product> findAllByCategory(Category category, Pageable pageable);

    List<Product> findProductsByCategory(Category category);

    @Query(value = "SELECT PRODUCT.* FROM PRODUCT, PRODUCT_PRODUCTPRICES WHERE PRODUCT_PRODUCTPRICES.PRODUCT_ID = PRODUCT.ID",
            nativeQuery = true)
    List<Product> findByStore(Store store);
}
