package ee.ttu.tarkvaratehnika.andmekogujad.spring.data.entity.scraper;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class CauseReport implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fileName;

    private String className;

    private String methodName;

    private Integer lineNumber;
}
