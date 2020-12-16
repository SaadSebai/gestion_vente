package com.projetTest.dao;

import java.util.List;


public interface IDao<T> {

	public List<T> findAll();
	public T findByName(String name);
	public boolean save(T p);
	public boolean update(T p);
	public boolean delete(T p);
}