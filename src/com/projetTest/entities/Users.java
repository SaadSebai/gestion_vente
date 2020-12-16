package com.projetTest.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="users")
public class Users implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int codeUser;
	
	@Column
	private String login;
	
	@Column
	private String pass;
	
	@OneToMany(mappedBy="users", fetch=FetchType.LAZY)
	private List<Commandes> commandes =new ArrayList<Commandes>();
	
	public Users() {
		
	}

	public Users(int codeUser, String login, String pass, List<Commandes> commandes) {
		
		this.codeUser = codeUser;
		this.login = login;
		this.pass = pass;
		this.commandes = commandes;
	}

	public int getCodeUser() {
		return codeUser;
	}

	public void setCodeUser(int codeUser) {
		this.codeUser = codeUser;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public List<Commandes> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commandes> commandes) {
		this.commandes = commandes;
	}

	@Override
	public String toString() {
		return "Users [codeUser=" + codeUser + ", login=" + login + ", pass=" + pass + "]";
	}

}
