package ee.ttu.tarkvaratehnika.andmekogujad.scraper.main;

import ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.maxima.MaximaScraper;
import ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.prisma.PrismaScraper;
import ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.selver.SelverScraper;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.scraper.model.ScraperReport;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.scraper.repository.ScraperService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.*;

@Component
public class ScraperMain {

    private static final Integer NR_OF_SCRAPERS = 3;

    @Getter
    @Autowired
    private ScraperService scraperService;

    private ScraperRunnable selver, maxima, prisma;
    private ExecutorService executor;
    private int activeRunnables;

    @Getter
    private ScraperReport scraperReport;

    public ScraperMain() {
        selver = new ScraperRunnable(new SelverScraper(), this);
        maxima = new ScraperRunnable(new MaximaScraper(), this);
        prisma = new ScraperRunnable(new PrismaScraper(), this);
    }

    public static void main(String[] args) {
        ScraperMain sm = new ScraperMain();
        System.out.println(sm.startUpdate());
        System.out.println(sm.startUpdate());
        System.out.println(sm.stopUpdate());
        System.out.println(sm.stopUpdate());
        System.out.println(sm.startUpdate());
        System.out.println(sm.startUpdate());
    }

    void notifyRunnableComplete() {
        synchronized (this) {
            activeRunnables--;
            if (activeRunnables == 0) {
                scraperReport.setEndDate(LocalDate.now());
                scraperReport.setEndTime(LocalTime.now());
                scraperService.saveReport(scraperReport);
            }
        }
    }

    public String stopUpdate() {
        if (executor != null && !executor.isTerminated()) {
            executor.shutdownNow();
            try {
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Update canceled";
        }
        return "Update is not running";
    }

    public String startUpdate() {
        if (executor == null || executor.isTerminated()) {
            scraperReport = new ScraperReport();
            scraperReport.setStartDate(LocalDate.now());
            scraperReport.setStartTime(LocalTime.now());
            executor = Executors.newFixedThreadPool(NR_OF_SCRAPERS);
            activeRunnables = NR_OF_SCRAPERS;
            executor.submit(selver);
            executor.submit(maxima);
            executor.submit(prisma);
            executor.shutdown();
            return "Update started successfully";
        }
        return "Update already running";
    }
}
