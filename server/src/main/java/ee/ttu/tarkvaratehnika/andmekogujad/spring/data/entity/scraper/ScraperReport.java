package ee.ttu.tarkvaratehnika.andmekogujad.spring.data.entity.scraper;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="scraperreport")
public class ScraperReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name="scraperreport_exceptions")
    private List<ExceptionReport> exceptions;

    private LocalDate startDate;

    private LocalTime startTime;

    private LocalDate endDate;

    private LocalTime endTime;

    public void addExceptionReport(ExceptionReport exceptionReport) {
        exceptions.add(exceptionReport);
    }
}
