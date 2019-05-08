package ee.ttu.tarkvaratehnika.andmekogujad.spring.data.entity.scraper;

import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Store;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class ExceptionReport implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalTime time;

    @Enumerated(EnumType.STRING)
    private Store scraper;

    @Column
    private String url;

    private String message;

    @Embedded
    private CauseReport causeReport;
}
