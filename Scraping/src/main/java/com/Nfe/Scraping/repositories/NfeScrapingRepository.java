package com.Nfe.Scraping.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Nfe.Scraping.models.NfeScrapingModel;

@Repository
public interface NfeScrapingRepository extends JpaRepository<NfeScrapingModel, Long> {

	List<NfeScrapingModel> findByUf(String uf);

}
