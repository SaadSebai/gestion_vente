package com.projetTest.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Commandes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int codeCmd;

	@Column
	private int qteCmd;

	@Column
	@Temporal(TemporalType.DATE)
	private Date dateCmd;

	@ManyToOne
	@JoinColumn(name = "codePdt")
	private ProduitsPrix produitsPrix;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "client", referencedColumnName = "login")
	private Users users;

	public Commandes(int codeCmd, int qteCmd, Date dateCmd, ProduitsPrix produitsPrix, Users users) {
		super();
		this.codeCmd = codeCmd;
		this.qteCmd = qteCmd;
		this.dateCmd = dateCmd;
		this.produitsPrix = produitsPrix;
		this.users = users;
	}

	public Commandes() {
		super();
	}

	public int getCodeCmd() {
		return codeCmd;
	}

	public void setCodeCmd(int codeCmd) {
		this.codeCmd = codeCmd;
	}

	public int getQteCmd() {
		return qteCmd;
	}

	public void setQteCmd(int qteCmd) {
		this.qteCmd = qteCmd;
	}

	public Date getDateCmd() {
		return dateCmd;
	}

	public void setDateCmd(Date dateCmd) {
		this.dateCmd = dateCmd;
	}

	public ProduitsPrix getProduitsPrix() {
		return produitsPrix;
	}

	public void setProduitsPrix(ProduitsPrix produitsPrix) {
		this.produitsPrix = produitsPrix;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Commandes [codeCmd=" + codeCmd + ", qteCmd=" + qteCmd + ", dateCmd=" + dateCmd + "]";
	}
	
	

}
