package ee.ttu.tarkvaratehnika.andmekogujad.spring.data.scraper.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class CauseReport {

    private String filename;

    private String className;

    private String methodName;

    private Integer lineNumber;
}
