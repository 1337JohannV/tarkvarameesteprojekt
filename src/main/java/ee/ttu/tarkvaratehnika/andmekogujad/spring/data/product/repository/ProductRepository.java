package ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.repository;

import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Category;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Store;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends CrudRepository<Product, Long>,
        PagingAndSortingRepository<Product, Long> {

    List<Product> findAllByCategory(Category category, Pageable pageable);

    List<Product> findProductsByCategory(Category category);

    @Query(value = "SELECT PRODUCT.* FROM PRODUCT, PRODUCT_PRODUCTPRICES WHERE PRODUCT_PRODUCTPRICES.PRODUCT_ID = PRODUCT.ID",
            nativeQuery = true)
    List<Product> findByStore(Store store);

    Product findByEan(String ean);

}
