package com.projetTest.ImpDao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.projetTest.dao.IDao;
import com.projetTest.entities.Commandes;

@Transactional("transactionManagerOne")
@EnableTransactionManagement
@Repository
public class ImpDaoCommandes implements IDao<Commandes> {

	@Autowired
	@Qualifier("sessionFactoryOne")
	SessionFactory sessionFactory;
	
	@Override
	public List<Commandes> findAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Commandes").list();
	}

	@Override
	public boolean save(Commandes p) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(p);
		return true;
	}

	@Override
	public boolean update(Commandes p) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(p);
		return true;
	}

	@Override
	public boolean delete(Commandes p) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(p);
		return true;
	}

	@Override
	public Commandes findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
