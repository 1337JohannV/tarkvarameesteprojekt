package ee.ttu.tarkvaratehnika.andmekogujad.scraper.main.exception;

import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Store;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.scraper.model.CauseReport;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.scraper.model.ExceptionReport;
import lombok.Getter;

import java.time.LocalTime;

public class ScraperException extends Exception {

    private static final long serialVersionUID = 1L;

    @Getter
    private ExceptionReport exceptionReport;

    public ScraperException() {
    }

    public ScraperException(Store store, String url, String message, Throwable cause) {
        super(message, cause);
        exceptionReport = new ExceptionReport();
        exceptionReport.setTime(LocalTime.now());
        StackTraceElement ste = cause.getStackTrace()[0];
        if (ste != null) {
            CauseReport causeReport = new CauseReport();
            causeReport.setFileName(ste.getFileName());
            causeReport.setClassName(ste.getClassName());
            causeReport.setMethodName(ste.getMethodName());
            causeReport.setLineNumber(ste.getLineNumber());
            exceptionReport.setCauseReport(causeReport);
        }
        exceptionReport.setScraper(store);
        exceptionReport.setUrl(url);
        exceptionReport.setMessage(message);
        System.out.println(exceptionReport.toString());
    }

    public static void main(String[] args) throws ScraperException {
        try {
            throw new ScraperException();
        } catch (Exception e) {
            throw new ScraperException(Store.PRISMA, "", "", e);
        }
    }
}