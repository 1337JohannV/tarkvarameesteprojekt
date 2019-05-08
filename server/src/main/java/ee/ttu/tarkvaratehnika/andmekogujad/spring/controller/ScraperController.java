package ee.ttu.tarkvaratehnika.andmekogujad.spring.controller;

import ee.ttu.tarkvaratehnika.andmekogujad.scraper.main.ScraperMain;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.service.product.ProductService;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.entity.scraper.ScraperReport;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.service.scraper.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/scraper")
@RestController
public class ScraperController {

    @Autowired
    private ScraperMain scraperMain;

    @Autowired
    private ScraperService scraperService;

    @Autowired
    private ProductService productService;


    @RequestMapping(path = "/update/start", method = RequestMethod.GET)
    public String startUpdate() {
        if (scraperMain.getIsRunning()) {
            return "Scraper is already running";
        }
        productService.deleteAll();
        scraperMain.startUpdate();
        return "Cleared table product entries and started Scraper";
    }


    @RequestMapping(path = "/status", method = RequestMethod.GET)
    public String getStatus() {
        if (scraperMain.getIsRunning()) {
            return "Scraper is running";
        }
        return "Scraper is not running";
    }

    @RequestMapping(path = "/report/all", method = RequestMethod.GET)
    public List<ScraperReport> getAllReports() {
        return scraperService.findAll();
    }

    @RequestMapping(path = "/report/latest", method = RequestMethod.GET)
    public ScraperReport getLatestReport() {
        return scraperService.findLatest();
    }
}
