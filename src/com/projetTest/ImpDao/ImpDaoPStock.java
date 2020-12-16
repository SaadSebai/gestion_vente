package com.projetTest.ImpDao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.projetTest.dao.IDao;
import com.projetTest.entities.ProduitsStock;

@Transactional("transactionManagerTwo")
@EnableTransactionManagement
@Repository
public class ImpDaoPStock implements IDao<ProduitsStock> {

	@Autowired
	@Qualifier("sessionFactoryTwo")
	SessionFactory sessionFactory;
	
	@Override
	public List<ProduitsStock> findAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from ProduitsStock").list();
	}

	@Override
	public ProduitsStock findByName(String name) {
		// TODO Auto-generated method stub
		return (ProduitsStock) sessionFactory.openSession().createQuery("from ProduitsStock where nomPdt =:name").setParameter("name", name).list().get(0);
	}

	@Override
	public boolean save(ProduitsStock p) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(p);
		return true;
	}

	@Override
	public boolean update(ProduitsStock p) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(p);
		return true;
	}

	@Override
	public boolean delete(ProduitsStock p) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(p);
		return true;
	}


}
