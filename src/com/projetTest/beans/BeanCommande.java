package com.projetTest.beans;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projetTest.ImpService.ImpIServiceCommandes;
import com.projetTest.ImpService.ImpIServicePStock;
import com.projetTest.ImpService.ImpIServiceProduitsPrix;
import com.projetTest.ImpService.ImpIServiceUsers;
import com.projetTest.entities.CmdFacture;
import com.projetTest.entities.Commandes;
import com.projetTest.entities.ProduitsPrix;
import com.projetTest.entities.ProduitsStock;
import com.projetTest.entities.Users;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean
@Component
public class BeanCommande {

	@Autowired
	ImpIServiceCommandes servicecommande;
	
	@Autowired
	ImpIServiceProduitsPrix servicepp;
	
	@Autowired
	ImpIServiceUsers serviceuser;
	
	@Autowired
	ImpIServicePStock servicestock;
	
	//la commande créer par l'utilisateur
	Commandes newCommande = new Commandes();
	
	//liste des commandes après la filtrage
	List<Commandes> filtredcmds;
	

	public List<Commandes> getFiltredcmds() {
		return filtredcmds;
	}

	public void setFiltredcmds(List<Commandes> filtredcmds) {
		this.filtredcmds = filtredcmds;
	}

	public Commandes getNewCommande() {
		return newCommande;
	}

	public void setNewCommande(Commandes newCommande) {
		this.newCommande = newCommande;
	}
	
	//liste des commandes
	public List<Commandes> getCmnds(){
		return servicecommande.findAll();
	}
	
	//liste des commaneds d'utilisateur
	public List<Commandes> getCmdsOfUser(String uname){
		
		List<Commandes> cmds = new ArrayList<Commandes>();
		
		for(Commandes c : servicecommande.findAll()) {
			if(c.getUsers().getLogin().equals(uname)) {
				cmds.add(c);
			}
		}
		
		return cmds;
		
	}
	
	//la méthode qui va créer la commande dans la BD
	public String commander(Users user, ProduitsPrix pp) {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		newCommande.setProduitsPrix(pp);
		newCommande.setUsers(user);
		
		//créer l'objet date d'aujourd'hui
		LocalDateTime dateNow = LocalDateTime.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date d = Date.from(dateNow.atZone(defaultZoneId).toInstant());
		newCommande.setDateCmd(d);
		

		String nn = pp.getNomP();
		
		ProduitsStock ps = servicestock.findByName(nn);
		
		//si la quantité entrée est 0
		if(ps.getQtePdt() == 0) {
			
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error",  "Ce produit est épuisé") );
			
			return "commande";
		}else {
			
			//soustraction de quantité de stock et la quantité entrée
			int q = ps.getQtePdt() - newCommande.getQteCmd();
			
			//si la resultat de soustraction des quantités est négative
			if(q < 0) {
				
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "error",  "La quantité de produit est "+ ps.getQtePdt() +"") );
				
				return "commande";
			}else {
				ps.setQtePdt(q);
				
				//modifier la quentité dans la table de stock
				servicestock.update(ps);
				
				//ajouter une novelle commande 
				servicecommande.save(newCommande);
				
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Creer avec succès",  "Votre commande est ajouté avec succes.") );
				
				return "facture";
			}
			
		}
		
	}
	
	@PostConstruct
    public void init() {
	}
	

	//methode creation de PDF
	public void printPDF(String pname, String uname) throws JRException, IOException {
		
		List<CmdFacture> datasource = new ArrayList<CmdFacture>();
		
		//date de facture
		Date d = newCommande.getDateCmd();
		
		//la facture
		int facture = newCommande.getQteCmd() * newCommande.getProduitsPrix().getPrixP();
		
		//les information de facture
		datasource.add(new CmdFacture(pname, uname, newCommande.getQteCmd(), facture, newCommande.getProduitsPrix().getPrixP(), d));
		
		//nom de PDF
		String filename = "Commande.pdf";
		
		//PDF path
		String jasperPath = "/resources/commandeFctr.jasper";
		
		this.PDf(null, jasperPath, datasource, filename);
	}
	
	//inisialisation des elements de PDF
	public void PDf(Map<String, Object> params, String jasperPath, List<?> dataSource, String fileName) throws JRException, IOException {
		
		String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
		
		File file = new File(relativeWebPath);
		
		JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(dataSource, false);
		
		JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, source);
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		
		response.addHeader("Content-disposition", "attachment;filename=" + fileName);
		
		ServletOutputStream stream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(print, stream);
		
		FacesContext.getCurrentInstance().responseComplete();
	}
	
}
