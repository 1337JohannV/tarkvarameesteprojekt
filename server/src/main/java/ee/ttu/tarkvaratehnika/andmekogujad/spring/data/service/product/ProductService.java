package ee.ttu.tarkvaratehnika.andmekogujad.spring.data.service.product;

import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Category;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Store;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.entity.product.Product;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Primary
@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findProductsByCategory(Category category) {
        return productRepository.findProductsByCategory(category);
    }

    public List<Product> findByStore(Store store) {
        return productRepository.findByStore(store);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        products.addAll(productRepository.findAll());
        return products;
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void saveAll(Iterable<Product> products) {
        productRepository.saveAll(products);
    }

    public List<Product> findAll(int page, int size, Sort.Direction direction, String orderBy) {
        return productRepository.findAll(PageRequest.of(page, size, direction, orderBy)).getContent();
    }

    public List<Product> findByCategory(Category category, int page, int size, Sort.Direction direction,
                                        String orderBy) {
        return productRepository.findAllByCategory(category, PageRequest.of(page, size, direction, orderBy));
    }

    public void deleteAll() {
        System.out.println("Started DELETING");
        productRepository.deleteAll();
        productRepository.flush();
        System.out.println("DELETING FINISHED");
    }

    public Long getRowsInCategory(Category category) {
        return productRepository.countByCategory(category);
    }

    public Long getProductRows() {
        return productRepository.count();
    }
}
