package br.ucb.fimes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import br.ucb.filmes.beans.Filme;


public class FilmeDAO {
	public void insert(Filme filme) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(filme);
		transaction.commit();
		session.close();
	}

	public void delete(Filme filme) throws ConstraintViolationException {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(filme);
		transaction.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<Filme> recoveryAll() {
		Session session = HibernateUtil.getSession();
		try {
			return session.createCriteria(Filme.class).list();
		} finally {
			session.close();
		}
	}
	public Filme consultar(long id) {
		Session session = HibernateUtil.getSession();
		try {
			Filme filme = (Filme) session.get(Filme.class, id);
			return filme;
		} finally {
			session.close();
		}
	}


}
