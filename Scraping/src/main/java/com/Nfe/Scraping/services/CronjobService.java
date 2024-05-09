package com.Nfe.Scraping.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CronjobService {
	
    @Autowired
    private NfeScrapingService nfeScrapingService;

    @Scheduled(fixedRate = 60000) // 5 minutos
    public void updateData() throws IOException {
    	nfeScrapingService.scrapeAndSaveData();
    }
}
