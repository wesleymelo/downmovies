package br.ucb.filmes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import br.ucb.filmes.beans.Categoria;

public class CategoriaDAO {
	public void insert(Categoria categoria) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(categoria);
		transaction.commit();
		session.close();
	}

	public void delete(Categoria categoria) throws ConstraintViolationException {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(categoria);
		transaction.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<Categoria> recoveryAll() {
		Session session = HibernateUtil.getSession();
		try {
			return session.createCriteria(Categoria.class).list();
		} finally {
			session.close();
		}
	}
	public Categoria consultar(long id) {
		Session session = HibernateUtil.getSession();
		try {
			Categoria Categoria = (Categoria) session.get(Categoria.class, id);
			return Categoria;
		} finally {
			session.close();
		}
	}


}
