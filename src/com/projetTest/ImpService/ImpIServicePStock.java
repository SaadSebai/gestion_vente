package com.projetTest.ImpService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetTest.dao.IDao;
import com.projetTest.entities.ProduitsStock;
import com.projetTest.service.IService;

@Service
public class ImpIServicePStock implements IService<ProduitsStock> {

	@Autowired
	IDao<ProduitsStock> dao;

	@Override
	public List<ProduitsStock> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public ProduitsStock findByName(String name) {
		// TODO Auto-generated method stub
		return dao.findByName(name);
	}

	@Override
	public boolean save(ProduitsStock p) {
		// TODO Auto-generated method stub
		dao.save(p);
		return true;
	}

	@Override
	public boolean update(ProduitsStock p) {
		// TODO Auto-generated method stub
		dao.update(p);
		return true;
	}

	@Override
	public boolean delete(ProduitsStock p) {
		// TODO Auto-generated method stub
		dao.delete(p);
		return true;
	}

}
