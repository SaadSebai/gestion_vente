package com.projetTest.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projetTest.ImpService.ImpIServicePStock;
import com.projetTest.ImpService.ImpIServiceProduitsPrix;
import com.projetTest.entities.Produit;
import com.projetTest.entities.ProduitsPrix;
import com.projetTest.entities.ProduitsStock;

@ManagedBean
@Component
public class BeanProduit {

	@Autowired
	ImpIServiceProduitsPrix serviceprix;
	
	@Autowired
	ImpIServicePStock servicestock;

	//produit de commande
	ProduitsPrix selectedProd;
	
	//liste des produits après filtrage
	List<ProduitsPrix> filtredprods;
	
	public List<ProduitsPrix> getFiltredprods() {
		return filtredprods;
	}

	public void setFiltredprods(List<ProduitsPrix> filtredprods) {
		this.filtredprods = filtredprods;
	}

	public ProduitsPrix getSelectedProd() {
		return selectedProd;
	}

	public void setSelectedProd(ProduitsPrix selectedProd) {
		this.selectedProd = selectedProd;
	}

	//liste des tout produits, les produits de cette liste contient des elements de produit stock et de produit vente
	public List<Produit> getPrds(){
		
		//liste des produits les produits qui vont être affichés
		List<Produit> prods = new ArrayList<Produit>();
		
		//liste des produits stock
		List<ProduitsStock> prodS = servicestock.findAll();
		
		//liste des produits vente
		List<ProduitsPrix> prodP = serviceprix.findAll();
		
		//liste des noms des produits vente
		List<String> prodsnames = new ArrayList<String>();
		
		//enregistrer les noms des produits de vente dans "prodsnames"
		for(ProduitsPrix pp : prodP) {
			prodsnames.add(pp.getNomP());
		}
		
		for(ProduitsStock p : prodS) {
			//si le nom de produit stock n'existe pas dans la liste des nom des produits vente
			//ajouter ce produit dans la BD de produit vente
			if(!prodsnames.contains(p.getNomPdt())) {
				ProduitsPrix newpp = new ProduitsPrix();
				newpp.setNomP(p.getNomPdt());
				newpp.setDescP(p.getDescPdt());
				newpp.setPrixP(p.getPrixPdt());
				
				serviceprix.save(newpp);
			}
		}
		
		//ajouter les produits stock dans la liste de tout les produit
		for(ProduitsStock p : prodS) {
			for(ProduitsPrix pp : prodP) {
				if(p.getNomPdt().equals(pp.getNomP())) {
					Produit prd = new Produit();
					prd.setCodeProduitStock(p.getCodePdt());
					prd.setNomProduitVente(pp.getNomP());
					prd.setPrixProduiVente(pp.getPrixP());
					prd.setQteProduitStock(p.getQtePdt());
					
					prods.add(prd);
				}
			}
		}
		
		return prods;
	}
	
	//commander un produit dans la table des produits
	public String commander(String nameProd) {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		//si la quentité de produit different de 0
		if(servicestock.findByName(nameProd).getQtePdt() != 0) {
			selectedProd = serviceprix.findByName(nameProd);
			return "commande";
		}
		else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur",  "Ce produit est épuisé") );
			return "index";
		}
	}
}
