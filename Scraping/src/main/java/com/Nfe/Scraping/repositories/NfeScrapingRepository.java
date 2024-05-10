package com.Nfe.Scraping.repositories;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Nfe.Scraping.models.NfeScrapingModel;

@Repository
public interface NfeScrapingRepository extends JpaRepository<NfeScrapingModel, Long> {

	List<NfeScrapingModel> findByUf(String uf);
	List<NfeScrapingModel> findByUfAndDateBetween(String uf, LocalDateTime start, LocalDateTime end);
	
//	@Query(value = "SELECT * FROM nfe_scraping_model WHERE uf = ?1 AND date = ?2", nativeQuery = true)
//    List<NfeScrapingModel> findByEstadoAndData(String uf, LocalDate date);
}
