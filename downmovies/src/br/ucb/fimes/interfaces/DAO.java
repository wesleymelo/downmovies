package br.ucb.fimes.interfaces;

import java.util.List;

public interface DAO<T> {
	
	public int insert(T obj);
	public void delete(int id);
	public void update(int id, T obj);
	public List<T> recoverAll();
	
}
