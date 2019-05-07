package ee.ttu.tarkvaratehnika.andmekogujad.spring.init;

import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.service.search.ProductSearch;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductSearchInitializingBean implements InitializingBean {

    @Autowired
    private ProductSearch productSearch;

    @Override
    public void afterPropertiesSet() {
        productSearch.init();
    }
}
