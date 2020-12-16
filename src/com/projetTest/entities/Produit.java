package com.projetTest.entities;

public class Produit {

	private int codeProduitStock;
	private String nomProduitVente;
	private int prixProduiVente;
	private int qteProduitStock;
	public int getCodeProduitStock() { 
		return codeProduitStock;
	}
	public void setCodeProduitStock(int codeProduitStock) {
		this.codeProduitStock = codeProduitStock;
	}
	public String getNomProduitVente() {
		return nomProduitVente;
	}
	public void setNomProduitVente(String nomProduitVente) {
		this.nomProduitVente = nomProduitVente;
	}
	public int getPrixProduiVente() {
		return prixProduiVente;
	}
	public void setPrixProduiVente(int prixProduiVente) {
		this.prixProduiVente = prixProduiVente;
	}
	public int getQteProduitStock() {
		return qteProduitStock;
	}
	public void setQteProduitStock(int qteProduitStock) {
		this.qteProduitStock = qteProduitStock;
	}
	@Override
	public String toString() {
		return "Produit [codeProduitStock=" + codeProduitStock + ", nomProduitVente=" + nomProduitVente
				+ ", prixProduiVente=" + prixProduiVente + ", qteProduitStock=" + qteProduitStock + "]";
	}
	
}
