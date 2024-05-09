package com.Nfe.Scraping.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

	private String getStatus(String imageName) {
		if (imageName.contains("bola_amarela")) {
			return "WARNING";
		}
		if (imageName.contains("bola_vermelha")) {
			return "DOWN";
		}
		return "UP";
	}

}
