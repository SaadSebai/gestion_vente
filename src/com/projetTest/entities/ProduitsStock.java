package com.projetTest.entities;

import javax.persistence.*;

@Entity
@Table(name="produits_stock")
public class ProduitsStock {
    
	@Id
	@GeneratedValue
	@Column
	private int codePdt;
	
	@Column
	private int qtePdt;
	
	@Column
	private String nomPdt;
	
	@Column
	private String descPdt;
	
	@Column
	private int prixPdt;
	
	public ProduitsStock() {
		
	}

	public ProduitsStock(int codePdt, int qtePdt, String nomPdt, String descPdt, int prixPdt) {
		super();
		this.codePdt = codePdt;
		this.qtePdt = qtePdt;
		this.nomPdt = nomPdt;
		this.descPdt = descPdt;
		this.prixPdt = prixPdt;
	}

	public int getCodePdt() {
		return codePdt;
	}

	public void setCodePdt(int codePdt) {
		this.codePdt = codePdt;
	}

	public int getQtePdt() {
		return qtePdt;
	}

	public void setQtePdt(int qtePdt) {
		this.qtePdt = qtePdt;
	}

	public String getNomPdt() {
		return nomPdt;
	}

	public void setNomPdt(String nomPdt) {
		this.nomPdt = nomPdt;
	}

	public String getDescPdt() {
		return descPdt;
	}

	public void setDescPdt(String descPdt) {
		this.descPdt = descPdt;
	}

	public int getPrixPdt() {
		return prixPdt;
	}

	public void setPrixPdt(int prixPdt) {
		this.prixPdt = prixPdt;
	}

	@Override
	public String toString() {
		return "ProduitsStock [codePdt=" + codePdt + ", qtePdt=" + qtePdt + ", nomPdt=" + nomPdt + ", descPdt="
				+ descPdt + ", prixPdt=" + prixPdt + "]";
	}
	
	
}
