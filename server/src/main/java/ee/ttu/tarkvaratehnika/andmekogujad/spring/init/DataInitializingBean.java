package ee.ttu.tarkvaratehnika.andmekogujad.spring.init;

import ee.ttu.tarkvaratehnika.andmekogujad.scraper.main.ScraperMain;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.service.ProductService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializingBean implements InitializingBean {

    @Autowired
    private ProductService productService;

    @Autowired
    private ScraperMain scraperMain;

    @Override
    public void afterPropertiesSet() {
        /*
        if (productService.findAll().size() == 0) {
            System.out.println("STARTING UPDATE");
            scraperMain.startUpdate();
        }
        System.out.println(productService.findAll().size());
        */
    }
}
