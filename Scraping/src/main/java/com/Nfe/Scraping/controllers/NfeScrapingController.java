package com.Nfe.Scraping.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    
    
//	@GetMapping("/status/{uf}/{datainicio}/{datafim}")
//	@GetMapping("/status")


}
