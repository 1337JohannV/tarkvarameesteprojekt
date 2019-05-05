package ee.ttu.tarkvaratehnika.andmekogujad.spring.data.scraper.repository;

import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.scraper.model.ScraperReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScraperRepository extends JpaRepository<ScraperReport, Long> {

    ScraperReport findFirstByOrderByIdDesc();

}
