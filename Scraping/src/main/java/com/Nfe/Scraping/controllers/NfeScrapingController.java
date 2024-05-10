package com.Nfe.Scraping.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Nfe.Scraping.models.NfeScrapingModel;
import com.Nfe.Scraping.repositories.NfeScrapingRepository;
import com.Nfe.Scraping.services.NfeScrapingService;

@RestController
@RequestMapping("/status")
public class NfeScrapingController {

	@Autowired
	NfeScrapingRepository nfeScrapingRepository;

	@Autowired
	NfeScrapingService nfeScrapingService;

	@GetMapping
	public List<NfeScrapingModel> getStatusService() {
		return nfeScrapingRepository.findAll();
	}

	@GetMapping("/{uf}")
	public List<NfeScrapingModel> getStatusServiceByUf(@PathVariable String uf) {
		return nfeScrapingService.getStatusByUf(uf);
	}

	@GetMapping("/{uf}/{date}")
	public List<NfeScrapingModel> getStatusServiceByUfAndDate(@PathVariable String uf,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		return nfeScrapingService.getStatusByUfAndDate(uf, date);
	}

	@GetMapping("/unavailable")
	public Map<String, Integer> getStatusServiceUnavailableByUf() {
		List<String> imageNames = nfeScrapingService.getImageNames();
		return nfeScrapingService.getUnavailabilityByStatus(imageNames);
	}

}
