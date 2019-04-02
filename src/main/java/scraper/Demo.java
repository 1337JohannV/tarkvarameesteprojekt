package scraper;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;
import scraper.prisma.PrismaScraper;
import scraper.prisma.PrismaUrlManager;

import java.util.ArrayList;
import java.util.List;

public class Demo {


    public static void main(String[] args) {
        Demo demo = new Demo();
        System.out.println(demo.getDemoData(Category.PUU_JA_KOOGIVILJAD));

    }

    public List<Product> getDemoData(Category category) {

        List<Product> demoData = new ArrayList<>();

        PrismaScraper prismaScraper = new PrismaScraper();

        List<String> catUrls = PrismaUrlManager.getSubCatUrls(category);

        int count = 80;

        assert catUrls != null;

        for (String url : catUrls) {

            List<String> productUrls = prismaScraper.getProductUrlsFromCategory(url);

            for (String productUrl : productUrls) {
                count--;

                String searchUrl = "https://www.prismamarket.ee" + productUrl;
                Product product = prismaScraper.getProductFromPage(searchUrl);
                product.setCategory(category);
                demoData.add(product);
                if (count == 0) {

                    break;
                }
            }
            if (count == 0) {
                break;
            }

        }
        return demoData;


    }
}
