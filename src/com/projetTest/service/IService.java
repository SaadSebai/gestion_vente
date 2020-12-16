package com.projetTest.service;

import java.util.List;

public interface IService<T> {

	public List<T> findAll();
	public T findByName(String name);
	public boolean save(T p);
	public boolean update(T p);
	public boolean delete(T p);
}
