package com.Nfe.Scraping.services;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
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
		List<NfeScrapingModel> nfeList = new ArrayList<>();
		for (Element row : table.select("tr")) {
			Elements cols = row.select("td");
			if (cols.size() >= 2) {
				final var uf = cols.get(0).text();
				nfeList.add(new NfeScrapingModel(uf, this.getStatus(cols.get(5).text()), LocalDateTime.now()));
			}
		}
		nfeScrapingRepository.saveAll(nfeList);
	}

    public List<NfeScrapingModel> getStatusByUf(String uf) {
        return nfeScrapingRepository.findByUf(uf);
    }

	public List<NfeScrapingModel> getStatusByUfAndDate(String uf, LocalDate date) {
		LocalDateTime startOfDay = date.atStartOfDay();
		LocalDateTime endOfTheDay = date.atTime(LocalTime.MAX);
		return nfeScrapingRepository.findByUfAndDateBetween(uf, startOfDay, endOfTheDay); 
	}
	

	  // Método para calcular a indisponibilidade dos serviços
    public Map<String, Integer> getUnavailabilityByStatus(List<String> imageNames) {
        Map<String, Integer> unavailabilityByStatus = new HashMap<>();

        for (String imageName : imageNames) {
            String status = getStatus(imageName);

            if (status.equals("DOWN")) {
            	unavailabilityByStatus.put(status, unavailabilityByStatus.getOrDefault(status, 0) + 1);
            }
        }

        return unavailabilityByStatus;
    }
	
    // Método para obter o status com base no nome da imagem
	private String getStatus(String imageName) {
		if (imageName.contains("bola_amarela")) {
			return "WARNING";
		}
		if (imageName.contains("bola_vermelha")) {
			return "DOWN";
		}
		return "UP";
	}
	
    public List<String> getImageNames() {
        List<String> imageNames = new ArrayList<>();
        imageNames.add("bola_vermelha");
        imageNames.add("bola_amarela");
        imageNames.add("bola_verde");
        
        return imageNames;
    }

}
