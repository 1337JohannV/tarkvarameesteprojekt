package ee.ttu.tarkvaratehnika.andmekogujad.spring.data.repository.scraper;

import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.entity.scraper.ScraperReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScraperRepository extends JpaRepository<ScraperReport, Long> {

    ScraperReport findFirstByOrderByIdDesc();

}
