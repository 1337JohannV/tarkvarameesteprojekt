package scraper;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;
import scraper.prisma.PrismaScraper;
import scraper.prisma.PrismaUrlManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Demo {



    public HashMap<Category, List<Product>> getDemoData(Category category) {

        HashMap<Category,List<Product>> demoData = new HashMap<>();

        PrismaScraper prismaScraper = new PrismaScraper();

        List<Product> products = new ArrayList<>();
        List<String> catUrls = PrismaUrlManager.getSubCatUrls(category);

        int count = 100;

        assert catUrls != null;

        for (String url : catUrls) {

            List<String> productUrls = prismaScraper.getProductUrlsFromCategory(url);

            for (String productUrl : productUrls) {
                count--;

                String searchUrl = "https://www.prismamarket.ee" + productUrl;
                Product product = prismaScraper.getProductDetails(searchUrl);
                product.setCategory(category);
                products.add(product);
                if(count == 0) {

                    break;
                }
            }
            if(count == 0) {
                break;
            }

        }
        demoData.put(category,products);
        System.out.println(demoData.get(category).size());
        return demoData;










    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        System.out.println(demo.getDemoData(Category.PUU_JA_KOOGIVILJAD));

    }
}
