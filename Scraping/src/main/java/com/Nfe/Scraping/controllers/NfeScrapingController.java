package com.Nfe.Scraping.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Nfe.Scraping.models.NfeScrapingModel;
import com.Nfe.Scraping.repositories.NfeScrapingRepository;

@RestController
public class NfeScrapingController {
	
	@Autowired
	NfeScrapingRepository nfeScrapingRepository;

	@GetMapping("/status")
	public ResponseEntity<List<NfeScrapingModel>> getCurrentStatus() {
		return ResponseEntity.status(HttpStatus.OK).body(nfeScrapingRepository.findAll());
	}
	
//	@GetMapping("/status/{uf}")
//	@GetMapping("/status/{uf}/{datainicio}/{datafim}")
//	@GetMapping("/status/indisponibilidade")


}
