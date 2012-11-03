package br.ucb.filmes.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

public class GenereicDAO<T> implements Serializable {

	private static final long serialVersionUID = 1L;


	@SuppressWarnings("rawtypes")
	private Class classe;

	@SuppressWarnings("rawtypes")
	public GenereicDAO() {
		this.classe = (Class) ((ParameterizedType)
				getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}


	public void insert(T objeto) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(objeto);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
	}

	public void delete(T object) throws
	ConstraintViolationException {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(object);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
	}


	@SuppressWarnings("unchecked")
	public List<T> recoveryAll() {
		Session session = HibernateUtil.getSession();
		List<T> lista = null;
		try {
			lista = session.createCriteria(this.classe).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	public T consult(Integer id) {
		Session session = HibernateUtil.getSession();
		T object = null;
		try {
			object = ((T) session.get(this.classe, id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return object;
	}
}
