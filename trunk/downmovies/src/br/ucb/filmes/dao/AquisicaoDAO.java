package br.ucb.filmes.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.ucb.filmes.beans.Aquisicao;

public class AquisicaoDAO extends GenericDAO<Aquisicao>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void insert(Aquisicao aquisicao) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(aquisicao);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Aquisicao> recoveryByUsuario(String email) {
		Session session = HibernateUtil.getSession();
		List<Aquisicao> aquisicoes = null;
		try {
			
			Query query = session.createQuery("from Aquisicao where email = '"+email+"'");
			aquisicoes = query.list();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return aquisicoes;
	}
	



}




