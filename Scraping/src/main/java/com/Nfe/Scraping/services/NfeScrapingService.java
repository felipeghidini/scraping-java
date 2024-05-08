package com.Nfe.Scraping.services;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.Nfe.Scraping.models.NfeScrapingModel;
import com.Nfe.Scraping.repositories.NfeScrapingRepository;

@Service
public class NfeScrapingService {
	
	 @Autowired
	    private NfeScrapingRepository nfeScrapingRepository;
	    
	    public void scrapeAndSaveData() throws IOException {
	        Document doc = Jsoup.connect("http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx").get();
	        Element table = doc.select("table.tabelaListagemDados").first();
	        
	        for (Element row : table.select("tr")) {
	            Elements cols = row.select("td");
	            if (cols.size() == 2) {
	                String uf = cols.get(0).text();
	                String status = cols.get(1).text();
	                NfeScrapingModel nfe = new NfeScrapingModel();
	                nfe.setUf(uf);
	                nfe.setStatus(status);
	                nfeScrapingRepository.save(nfe);
	            }
	        }
	    }
	    
//	    public void scrapeAndSaveData() {
//	        try {
//	            Document doc = Jsoup.connect("http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx").get();
//	            Elements tables = doc.select("table.tabelaListagemDados");
//
//	            for (Element table : tables) {
//	                Elements rows = table.select("tr");
//
//	                for (Element row : rows) {
//	                    Elements cols = row.select("td");
//	                    if (cols.size() == 5) { // Verifica se a linha contém os dados desejados
//	                        String uf = cols.get(0).text();
//	                        String status = cols.get(1).text();
//
//	                        // Salvar os dados no banco de dados
//	                        NfeScrapingModel nfe = new NfeScrapingModel();
//	                        nfe.setUf(uf);
//	                        nfe.setStatus(status);
//	                        nfeScrapingRepository.save(nfe);
//	                    }
//	                }
//	            }
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	    }
}

