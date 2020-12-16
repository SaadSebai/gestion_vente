package com.projetTest.ImpService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetTest.dao.IDao;
import com.projetTest.entities.Commandes;
import com.projetTest.service.IService;

@Service
public class ImpIServiceCommandes implements IService<Commandes> {

	@Autowired
	IDao<Commandes> dao;

	@Override
	public List<Commandes> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public boolean save(Commandes p) {
		// TODO Auto-generated method stub
		dao.save(p);
		return true;
	}

	@Override
	public boolean update(Commandes p) {
		// TODO Auto-generated method stub
		dao.update(p);
		return true;
	}

	@Override
	public boolean delete(Commandes p) {
		// TODO Auto-generated method stub
		dao.delete(p);
		return true;
	}

	@Override
	public Commandes findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
