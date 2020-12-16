package com.projetTest.ImpDao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.projetTest.dao.IDao;
import com.projetTest.entities.ProduitsPrix;

@Transactional("transactionManagerOne")
@EnableTransactionManagement
@Repository
public class ImpDaoProduits implements IDao<ProduitsPrix> {
	
	@Autowired
	@Qualifier("sessionFactoryOne")
	SessionFactory sessionFactory;

	@Override
	public List<ProduitsPrix> findAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from ProduitsPrix").list();
	}
	
	public ProduitsPrix findByName(String name) {
		// TODO Auto-generated method stub
		return (ProduitsPrix) sessionFactory.openSession().createQuery("from ProduitsPrix where nomPdt =:name").setParameter("name", name).list().get(0);
	}

	@Override
	public boolean save(ProduitsPrix p) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(p);
		return true;
	}

	@Override
	public boolean update(ProduitsPrix p) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(p);
		return true;
	}

	@Override
	public boolean delete(ProduitsPrix p) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(p);
		return true;
	}
}
