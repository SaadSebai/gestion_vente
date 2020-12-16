package com.projetTest.ImpService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetTest.dao.IDao;
import com.projetTest.entities.Users;
import com.projetTest.service.IService;

@Service
public class ImpIServiceUsers implements IService<Users> {

	@Autowired
	IDao<Users> dao;

	@Override
	public List<Users> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Users findByName(String name) {
		// TODO Auto-generated method stub
		return dao.findByName(name);
	}

	@Override
	public boolean save(Users p) {
		// TODO Auto-generated method stub
		dao.save(p);
		return true;
	}

	@Override
	public boolean update(Users p) {
		// TODO Auto-generated method stub
		dao.update(p);
		return true;
	}

	@Override
	public boolean delete(Users p) {
		// TODO Auto-generated method stub
		dao.delete(p);
		return true;
	}
	
}
