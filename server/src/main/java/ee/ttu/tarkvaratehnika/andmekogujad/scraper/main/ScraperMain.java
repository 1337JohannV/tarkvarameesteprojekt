package ee.ttu.tarkvaratehnika.andmekogujad.scraper.main;

import ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.maxima.MaximaRunnable;
import ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.prisma.PrismaRunnable;
import ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.selver.SelverRunnable;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.scraper.model.ScraperReport;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.scraper.service.ScraperService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class ScraperMain {

    @Getter
    @Autowired
    private ScraperService scraperService;

    @Autowired
    private SelverRunnable selverRunnable;

    @Autowired
    private MaximaRunnable maximaRunnable;

    @Autowired
    private PrismaRunnable prismaRunnable;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Getter
    private ScraperReport scraperReport;

    @Getter
    private Boolean isRunning = false;

    public void startUpdate() {
        if (!isRunning) {
            taskExecutor = new ThreadPoolTaskExecutor();
            taskExecutor.setCorePoolSize(3);
            taskExecutor.setMaxPoolSize(3);
            taskExecutor.setWaitForTasksToCompleteOnShutdown(false);
            taskExecutor.setThreadNamePrefix("scraper_task_executor_thread");
            taskExecutor.initialize();
            scraperReport = new ScraperReport();
            scraperReport.setStartDate(LocalDate.now());
            scraperReport.setStartTime(LocalTime.now());
            taskExecutor.execute(prismaRunnable);
            taskExecutor.execute(selverRunnable);
            taskExecutor.execute(maximaRunnable);

            while (true) {
                isRunning = true;
                int count = taskExecutor.getActiveCount();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (count == 0) {
                    taskExecutor.shutdown();
                    scraperReport.setEndDate(LocalDate.now());
                    scraperReport.setEndTime(LocalTime.now());
                    scraperService.saveReport(scraperReport);
                    scraperReport = null;
                    taskExecutor = null;
                    break;
                }
            }
            isRunning = false;
        }

    }
}
