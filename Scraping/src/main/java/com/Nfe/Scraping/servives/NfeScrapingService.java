package com.Nfe.Scraping.servives;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.Nfe.Scraping.models.NfeScrapingModel;
import com.Nfe.Scraping.repositories.NfeScrapingRepository;

@Component
public class NfeScrapingService {
	
	 @Autowired
	    private NfeScrapingRepository nfeScrapingRepository;

	    @Scheduled(fixedRate = 3600000) // Executa a cada hora
	    public void scrapeAndSaveData() {
	        try {
	            Document doc = Jsoup.connect("http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx").get();
	            Elements tables = doc.select("table.tabelaListagemDados");

	            for (Element table : tables) {
	                Elements rows = table.select("tr");

	                for (Element row : rows) {
	                    Elements cols = row.select("td");
	                    if (cols.size() == 5) { // Verifica se a linha contém os dados desejados
	                        String uf = cols.get(0).text();
	                        String status = cols.get(1).text();

	                        // Salvar os dados no banco de dados
	                        NfeScrapingModel nfe = new NfeScrapingModel();
	                        nfe.setUf(uf);
	                        nfe.setStatus(status);
	                        nfeScrapingRepository.save(nfe);
	                    }
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
}
