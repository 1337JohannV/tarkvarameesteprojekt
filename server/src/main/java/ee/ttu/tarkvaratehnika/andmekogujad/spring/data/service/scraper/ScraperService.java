package ee.ttu.tarkvaratehnika.andmekogujad.spring.data.service.scraper;

import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Currency;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Store;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.entity.product.Price;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.entity.product.Product;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.entity.product.ProductPrice;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.entity.product.Quantity;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.repository.product.ProductRepository;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.search.ProductSearch;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.entity.scraper.ScraperReport;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.repository.scraper.ScraperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class ScraperService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ScraperRepository scraperRepository;

    @Autowired
    private ProductSearch productSearch;

    public ScraperReport findLatest() {
        return scraperRepository.findFirstByOrderByIdDesc();
    }

    public List<ScraperReport> findAll() {
        List<ScraperReport> reports = new ArrayList<>();
        scraperRepository.findAll().forEach(reports::add);
        return reports;
    }

    public ScraperReport saveReport(ScraperReport scraperReport) {
        return scraperRepository.save(scraperReport);
    }

    public synchronized void updateProduct(Product scrapedProduct) {
        Product dbProduct = null;
        if (scrapedProduct.getEan() != null) {
            dbProduct = productRepository.findByEan(scrapedProduct.getEan());
        }
        if (dbProduct == null) {
            scrapedProduct.setBasePrice(convertToBasePrice(getMinPrice(scrapedProduct)));
            scrapedProduct.setBaseWeight(convertToBaseWeight(scrapedProduct.getQuantity()));
            productRepository.save(scrapedProduct);
            System.out.println("Saved new product: " + scrapedProduct);
        } else {
            Product mergedProduct = mergeProducts(dbProduct, scrapedProduct);
            productRepository.delete(dbProduct);
            productRepository.save(mergedProduct);
            System.out.println("Merged exisisting product: " + mergedProduct);
        }

    }

    private synchronized Product findTopCandidate(Product product) {
        HashMap<Long, Integer> candidates = new HashMap<>();
        if (product.getName() != null && product.getName().length() > 0) {
            updateCandidates(candidates, productSearch.searchByName(product.getName()), 8);
        }
        /*
        if (product.getProducer() != null && product.getProducer().length() > 0) {
            updateCandidates(candidates, productSearch.searchByProducer(product.getProducer()), 4);
        }
        if (product.getOrigin() != null && product.getOrigin().length() > 0) {
            updateCandidates(candidates, productSearch.searchByOrigin(product.getOrigin()), 2);
        }
        */
        System.out.println("\n\n");
        System.out.println("ABABABABABABAB");
        System.out.println(product);

        if (candidates.size() > 0) {
            Long id = Collections.max(candidates.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
            if (id != null) {
                Product candidate = productRepository.findById(id).orElse(null);
                System.out.println(candidate);
                System.out.println("\n\n");
                return candidate;
            }
        }
        return null;
    }

    private void updateCandidates(HashMap<Long, Integer> candidateMap, List candidates, int weight) {
        for (Object o : candidates) {
            if (o.getClass() == Product.class) {
                Long id = ((Product) o).getId();
                candidateMap.put(id, candidateMap.getOrDefault(id, 0) + weight);
            }
        }
    }

    private Product mergeProducts(Product dbProduct, Product scrapedProduct) {
        Product finalProduct = new Product();

        // Database ID
        finalProduct.setId(dbProduct.getId());

        // EAN
        if (dbProduct.getEan() != null) {
            finalProduct.setEan(dbProduct.getEan());
        } else if (scrapedProduct.getEan() != null) {
            finalProduct.setEan(scrapedProduct.getEan());
        }

        // Category
        finalProduct.setCategory(dbProduct.getCategory());

        // Name
        if (dbProduct.getName() != null) {
            finalProduct.setName(dbProduct.getName());
        } else if (scrapedProduct.getName() != null) {
            finalProduct.setName(scrapedProduct.getName());
        }

        // Merge prices
        List<ProductPrice> productPrices = new ArrayList<>(scrapedProduct.getProductPrices());
        Store store = productPrices.get(0).getStore();

        for (ProductPrice price : dbProduct.getProductPrices()) {
            if (price.getStore() != store) {
                productPrices.add(price);
            }
        }

        finalProduct.setProductPrices(productPrices);

        // Quantity
        if (dbProduct.getQuantity() != null) {
            finalProduct.setQuantity(dbProduct.getQuantity());
        } else if (scrapedProduct.getQuantity() != null) {
            finalProduct.setQuantity(scrapedProduct.getQuantity());
        }

        // Producer
        if (dbProduct.getProducer() != null) {
            finalProduct.setProducer(dbProduct.getProducer());
        } else if (scrapedProduct.getProducer() != null) {
            finalProduct.setProducer(scrapedProduct.getProducer());
        }

        // Origin
        if (dbProduct.getOrigin() != null) {
            finalProduct.setOrigin(dbProduct.getOrigin());
        } else if (scrapedProduct.getOrigin() != null) {
            finalProduct.setOrigin(scrapedProduct.getOrigin());
        }

        // Img URL
        if (dbProduct.getImgUrl() != null) {
            finalProduct.setImgUrl(dbProduct.getImgUrl());
        } else if (scrapedProduct.getImgUrl() != null) {
            finalProduct.setImgUrl(scrapedProduct.getImgUrl());
        }

        // Base price and weight
        finalProduct.setBasePrice(convertToBasePrice(getMinPrice(finalProduct)));
        finalProduct.setBaseWeight(convertToBaseWeight(finalProduct.getQuantity()));

        return finalProduct;
    }

    private Price getMinPrice(Product product) {
        Price priceToSet = new Price();
        priceToSet.setCurrency(Currency.EUR);
        double amount = product
                .getProductPrices()
                .stream()
                .filter(p -> Objects.nonNull(p.getRegularPrice().getAmount()))
                .map(p -> p.getRegularPrice().getAmount())
                .min(Double::compare).orElse(0.0);
        priceToSet.setAmount(amount);
        return priceToSet;
    }

    // Base weight is g / ml / tk
    private double convertToBaseWeight(Quantity quantity) {
        if (quantity == null || quantity.getValue() == null || quantity.getUnit() == null) {
            return 0.0;
        }
        double value = quantity.getValue();
        switch (quantity.getUnit()) {
            case KG:
                return value * 1000;
            case L:
                return value * 1000;
            case CL:
                return value * 10;
            case UNDEFINED:
                return 0.0;
            default:
                return value;
        }
    }

    // Base currency is eur
    private double convertToBasePrice(Price price) {
        if (price == null || price.getCurrency() == null || price.getAmount() == null) {
            return 0.0;
        }
        double amount = price.getAmount();
        switch (price.getCurrency()) {
            case EUR:
                return amount;
            case USD:
                return amount * 0.88;
            case UNDEFINED:
                return 0.0;
        }
        return amount;
    }
}
