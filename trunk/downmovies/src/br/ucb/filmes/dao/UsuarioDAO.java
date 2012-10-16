package br.ucb.filmes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import br.ucb.filmes.beans.Categoria;
import br.ucb.filmes.beans.Filme;
import br.ucb.filmes.beans.Usuario;

public class UsuarioDAO {
	public void insert(Usuario usuario) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(usuario);
		transaction.commit();
		session.close();
	}

	public void delete(Usuario usuario) throws ConstraintViolationException {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(usuario);
		transaction.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<Categoria> recoveryAll() {
		Session session = HibernateUtil.getSession();
		try {
			return session.createCriteria(Usuario.class).list();
		} finally {
			session.close();
		}
	}
	public Usuario consult(int id) {
		Session session = HibernateUtil.getSession();
		try {
			Usuario usuario = (Usuario) session.get(Usuario.class, id);
			return usuario;
		} finally {
			session.close();
		}
	}

}




