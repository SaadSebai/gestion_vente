package com.projetTest.entities;

import java.util.Date;

public class CmdFacture {

	private String nameProd;
	private String nameUser;
	private int qteCmd;
	private int prixCmd;
	private int prodprix;
	private Date dateCmd;
	
	public CmdFacture() {
		super();
	}

	public CmdFacture(String nameProd, String nameUser, int qteCmd, int prixCmd, int prodprix, Date dateCmd) {
		super();
		this.nameProd = nameProd;
		this.nameUser = nameUser;
		this.qteCmd = qteCmd;
		this.prixCmd = prixCmd;
		this.prodprix = prodprix;
		this.dateCmd = dateCmd;
	}

	public String getNameProd() {
		return nameProd;
	}

	public void setNameProd(String nameProd) {
		this.nameProd = nameProd;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public int getQteCmd() {
		return qteCmd;
	}

	public void setQteCmd(int qteCmd) {
		this.qteCmd = qteCmd;
	}

	public int getPrixCmd() {
		return prixCmd;
	}

	public void setPrixCmd(int prixCmd) {
		this.prixCmd = prixCmd;
	}

	public int getProdprix() {
		return prodprix;
	}

	public void setProdprix(int prodprix) {
		this.prodprix = prodprix;
	}

	public Date getDateCmd() {
		return dateCmd;
	}

	public void setDateCmd(Date dateCmd) {
		this.dateCmd = dateCmd;
	}
	
	
	
}
