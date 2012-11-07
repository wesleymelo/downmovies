package br.ucb.filmes.interfaces;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

public interface DAO<T> {
	
	void insert(T object);
	void delete(T object) throws ConstraintViolationException;
	void update(T object);
	List<T> recoveryAll();
	T consult(Integer id);
	
}
