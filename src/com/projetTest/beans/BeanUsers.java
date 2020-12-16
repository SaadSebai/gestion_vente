package com.projetTest.beans;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projetTest.ImpService.ImpIServiceUsers;
import com.projetTest.entities.Users;

@ManagedBean
@RequestScoped
@Component
public class BeanUsers {

	@Autowired
	ImpIServiceUsers serviceusers;
	
	//creation d'un objet qui va contient les informations d'utilisateur
	Users LoginUser = new Users();
	
	//la valeur d'inpot de cofirmation de mote de pass
	String passConf;
	
	//les getters et les setters
	public String getPassConf() {
		return passConf;
	}

	public void setPassConf(String passConf) {
		this.passConf = passConf;
	}

	public Users getLoginUser() {
		return LoginUser;
	}

	public void setLoginUser(Users loginUser) {
		LoginUser = loginUser;
	}

	//la m�thode qui va inscrie l'utilisateur dans l'application
	public String regester() throws IOException {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		//ce programme va tester si le nom d'utilisateur d�ja existe dans la base de donne
		for(Users u : serviceusers.findAll()) {
			if(u.getLogin().equals(LoginUser.getLogin())) {
				//si le nom d�ja existe, le programme va afficher un message d'erreur
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Blocker",  "Ce nom existe d�j�") );
				return "Regester";
			}
		}
		
		if(LoginUser.getPass().equals(passConf)) {
			//si les informations sont valides, on appel� la m�thode save pour enregistrer ces information
			serviceusers.save(LoginUser);
			//apr�s on va afficher un message de succ�s
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cr�er avec succ�s",  "Votre compte est ajout� avec succes.") );
			return "index";
		}else {
			//sinon, le programme va afficher un message d'erreur
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Blocker",  "Confirmer votre mot de passe") );
		}
		
		return "Regester";
	}
	
	//la methode qui fait le login d'utilisateur
	public String login() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		//chercher l'utilisateur avec les informations des les inputs 
		for(Users u : serviceusers.findAll())
			//si oui, la login est valide
			if(u.getLogin().equals(LoginUser.getLogin()) && u.getPass().equals(LoginUser.getPass())) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bonjour", "Login avec succ�s") );
				return "index";
			}
		//sinon le login est fable
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Echou�",  "Ces informations ne sont pas correct") );
		return "login";
	}
	
	//la methode de modification de mote de pass d'utilisateur
	public String modifier() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		//si la confirmation est valide, modifier la mote de pass avec un message de succ�s
		if(LoginUser.getPass().equals(passConf)) {
			String name= LoginUser.getLogin();
			String pass = LoginUser.getPass();
			Users user = serviceusers.findByName(name);
			
			user.setPass(pass);
			
			serviceusers.update(user);
			
			System.out.println(user);
			
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modifer",  "Modifer avec succe�") );

			return "index";
		}
		//sinon, afficher un message d'erreur
		else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Opss!",  "Confirmer votre mot de passe") );
			return "editInfo";
		}
	}
	
	//methode de rediriction vers la page de regester 
	public String goToReg() {
		System.out.println("going to regester");
		return "Regester";
	}
	
	//methode de rediriction vers la page de login
	public String goToLogin() {
		System.out.println("going to login");
		return "login";
	}
	
	//methode de rediriction vers la page de modification de mot de pass
	public String goToModifier() {
		System.out.println("going to modifier");
		return "editInfo";
	}
}
