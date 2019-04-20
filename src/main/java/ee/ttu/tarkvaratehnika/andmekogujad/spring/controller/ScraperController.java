package ee.ttu.tarkvaratehnika.andmekogujad.spring.controller;


import ee.ttu.tarkvaratehnika.andmekogujad.scraper.main.ScraperMain;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.scraper.model.ScraperReport;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.scraper.repository.ScraperService;
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


    @RequestMapping(
            path = "/update/start",
            method = RequestMethod.GET)
    public String runUpdate() {
        return scraperMain.startUpdate();
    }


    @RequestMapping(
            path = "/update/stop",
            method = RequestMethod.GET)
    public String stopUpdate() {
        return scraperMain.stopUpdate();
    }

    @RequestMapping(
            path = "/report/all",
            method = RequestMethod.GET)
    public List<ScraperReport> getAllReports() {
        return scraperService.findAll();
    }

    @RequestMapping(
            path = "/report/latest",
            method = RequestMethod.GET)
    public ScraperReport getLatestReport() {
        return scraperService.findLatest();
    }
}
