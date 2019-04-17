package ee.ttu.tarkvaratehnika.andmekogujad.spring.data.scraper.model;

import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Store;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@Embeddable
public class ExceptionReport {

    private LocalTime time;

    @Enumerated(EnumType.STRING)
    private Store scraper;

    @Column
    private String url;

    private String message;

    @Embedded
    private CauseReport causeReport;
}
