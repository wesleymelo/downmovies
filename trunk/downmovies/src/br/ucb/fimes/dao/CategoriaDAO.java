package br.ucb.fimes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import br.ucb.filmes.beans.Categoria;

public class CategoriaDAO {
	public void salvar(Categoria Categoria) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(Categoria);
		transaction.commit();
		session.close();
	}

	public void excluir(Categoria Categoria) throws ConstraintViolationException {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(Categoria);
		transaction.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<Categoria> listar() {
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
