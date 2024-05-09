package com.Nfe.Scraping.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class NfeScrapingModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String uf;

	@Column
	private String status;

	@Column
	private LocalDateTime insertDateTime;

	public NfeScrapingModel() {
	}

	public NfeScrapingModel(String uf, String status, LocalDateTime insertDateTime) {
		this.uf = uf;
		this.status = status;
		this.insertDateTime = insertDateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
