package ee.ttu.tarkvaratehnika.andmekogujad.spring.data.scraper.repository;

import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.model.Product;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.repository.search.ProductSearch;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.scraper.model.ScraperReport;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
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

    public void updateProduct(Product scrapedProduct) {
        synchronized (this) {
            Product dbProduct;
            if (scrapedProduct.getEan() != null) {
                dbProduct = productRepository.findByEan(scrapedProduct.getEan());
                if (dbProduct == null) {
                    dbProduct = findTopCandidate(scrapedProduct);
                }
            } else {
                dbProduct = findTopCandidate(scrapedProduct);
            }
            if (dbProduct == null) {
                productRepository.save(scrapedProduct);
            } else {
                Product mergedProduct = mergeProducts(dbProduct, scrapedProduct);
                productRepository.delete(dbProduct);
                productRepository.save(mergedProduct);
            }
        }
    }

    private Product findTopCandidate(Product p) {
        HashMap<Long, Integer> candidates = new HashMap<>();
        if (p.getName() != null) {
            updateCandidates(candidates, productSearch.searchByName(p.getName()));
        } if (p.getProducer() != null) {
            updateCandidates(candidates, productSearch.searchByProducer(p.getProducer()));
        } if (p.getOrigin() != null) {
            updateCandidates(candidates, productSearch.searchByOrigin(p.getOrigin()));
        }
        if (candidates.size() > 0) {
            Long id = Collections.max(candidates.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
            if (id != null) {
                return productRepository.findById(id).orElse(null);
            }
        }
        return null;
    }

    private void updateCandidates(HashMap<Long, Integer> candidateMap, List candidates) {
        for (Object o : candidates) {
            if (o.getClass() == Product.class) {
                Long id = ((Product) o).getId();
                candidateMap.put(id, candidateMap.getOrDefault(id, 0) + 1);
            }
        }
    }

    private Product mergeProducts(Product dbProduct, Product scrapedProduct) {
        Product finalProduct = new Product();
        finalProduct.setId(dbProduct.getId());
        //TODO: merge the product from database and scraped product

        return finalProduct;
    }
}
