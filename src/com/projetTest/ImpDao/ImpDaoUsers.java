package com.projetTest.ImpDao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.projetTest.dao.IDao;
import com.projetTest.entities.Users;

@Transactional("transactionManagerOne")
@EnableTransactionManagement
@Repository
public class ImpDaoUsers implements IDao<Users> {

	@Autowired
	@Qualifier("sessionFactoryOne")
	SessionFactory sessionFactory;
	
	@Override
	public List<Users> findAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Users").list();
	}

	@Override
	public Users findByName(String name) {
		// TODO Auto-generated method stub
		return (Users) sessionFactory.openSession().createQuery("from Users where login =:name").setParameter("name", name).list().get(0);
	}

	@Override
	public boolean save(Users p) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(p);
		return true;
	}

	@Override
	public boolean update(Users p) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().merge(p);
		return true;
	}

	@Override
	public boolean delete(Users p) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(p);
		return true;
	}

}
