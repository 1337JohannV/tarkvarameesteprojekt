package ee.ttu.tarkvaratehnika.andmekogujad.spring.data.scraper.repository;

import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.scraper.model.ScraperReport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ScraperRepository extends CrudRepository<ScraperReport, Long> {

    ScraperReport findFirstByOrderByIdDesc();

}
