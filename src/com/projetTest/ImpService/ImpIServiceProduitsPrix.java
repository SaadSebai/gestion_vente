package com.projetTest.ImpService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetTest.dao.IDao;
import com.projetTest.entities.ProduitsPrix;
import com.projetTest.service.IService;

@Service
public class ImpIServiceProduitsPrix implements IService<ProduitsPrix> {

	@Autowired
	IDao<ProduitsPrix> dao;
	
	@Override
	public List<ProduitsPrix> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public ProduitsPrix findByName(String name) {
		// TODO Auto-generated method stub
		return dao.findByName(name);
	}

	@Override
	public boolean save(ProduitsPrix p) {
		// TODO Auto-generated method stub
		dao.save(p);
		return true;
	}

	@Override
	public boolean update(ProduitsPrix p) {
		// TODO Auto-generated method stub
		dao.update(p);
		return true;
	}

	@Override
	public boolean delete(ProduitsPrix p) {
		// TODO Auto-generated method stub
		dao.delete(p);
		return true;
	}


}
