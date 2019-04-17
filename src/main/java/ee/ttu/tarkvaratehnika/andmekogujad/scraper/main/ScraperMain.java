package ee.ttu.tarkvaratehnika.andmekogujad.scraper.main;

import ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.maxima.MaximaScraper;
import ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.prisma.PrismaScraper;
import ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.selver.SelverScraper;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.scraper.model.ExceptionReport;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.scraper.model.ScraperReport;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.scraper.repository.ScraperService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ScraperMain {

    private static final Integer NR_OF_SCRAPERS = 3;

    @Getter
    @Autowired
    private ScraperService scraperService;

    private List<ExceptionReport> exceptions;
    private ScraperReport scraperReport;
    private ScraperRunnable selver, maxima, prisma;
    private ExecutorService executor;
    private int activeRunnables;

    public ScraperMain() {
        activeRunnables = 0;
        exceptions = new ArrayList<>();
        selver = new ScraperRunnable(new SelverScraper(), this);
        maxima = new ScraperRunnable(new MaximaScraper(), this);
        prisma = new ScraperRunnable(new PrismaScraper(), this);
    }

    public static void main(String[] args) {
        ScraperMain main = new ScraperMain();
        System.out.println(main.startUpdate());
        System.out.println(main.stopUpdate());
        System.out.println(main.startUpdate());
        System.out.println(main.stopUpdate());
        System.out.println(main.startUpdate());
        System.out.println(main.stopUpdate());
        System.out.println(main.stopUpdate());
    }

    synchronized void notifyRunnableComplete(List<ExceptionReport> exceptions) {
        activeRunnables--;
        scraperReport.setExceptions(
                Stream.of(scraperReport.getExceptions(), exceptions)
                        .flatMap(List::stream)
                        .collect(Collectors.toList()));
        if (activeRunnables == 0) {
            scraperReport.setExceptions(this.exceptions);
            scraperReport.setEndDate(LocalDate.now());
            scraperReport.setEndTime(LocalTime.now());
            executor.shutdownNow();
            executor = null;
        }
    }

    public ScraperReport saveReport() {
        if (scraperReport != null) {
            return scraperService.saveReport(scraperReport);
        }
        return null;
    }

    public synchronized String stopUpdate() {
        if (executor != null) {
            if (executor.isShutdown() || executor.isTerminated()) {
                return "Update already canceled";
            } else if (activeRunnables > 0) {
                executor.shutdownNow();
                return "Update canceled";
            }
        }
        return "Update is not running";
    }

    public String startUpdate() {
        if (scraperReport != null) {
            saveReport();
        }
        if (activeRunnables == 0 && (executor == null || executor.isTerminated())) {
            scraperReport = new ScraperReport();
            scraperReport.setStartDate(LocalDate.now());
            scraperReport.setStartTime(LocalTime.now());

            executor = Executors.newFixedThreadPool(NR_OF_SCRAPERS);

            activeRunnables = NR_OF_SCRAPERS;

            executor.submit(selver);
            executor.submit(maxima);
            executor.submit(prisma);

            return "Update started successfully";
        }
        return "Update already running";
    }
}
