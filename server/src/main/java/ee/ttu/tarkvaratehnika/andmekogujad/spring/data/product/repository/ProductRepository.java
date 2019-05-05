package ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.repository;

import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Category;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Store;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory(Category category, Pageable pageable);

    List<Product> findProductsByCategory(Category category);

    @Query(value = "SELECT PRODUCT.* FROM PRODUCT, PRODUCT_PRODUCTPRICES WHERE PRODUCT_PRODUCTPRICES.PRODUCT_ID = PRODUCT.ID AND store",
            nativeQuery = true)
    List<Product> findByStore(Store store);

    Product findByEan(String ean);

}
