package com.tarkvaramehed.projekt.tarkvarameesteprojekt.init;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.data.search.ProductSearch;
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
