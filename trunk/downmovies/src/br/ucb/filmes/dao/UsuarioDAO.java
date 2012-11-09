package br.ucb.filmes.dao;

import org.hibernate.Session;

import br.ucb.filmes.beans.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Usuario consult(String email) {
		Session session = HibernateUtil.getSession();
		Usuario usuario = null;
		try {
			usuario = ((Usuario) session.get(Usuario.class, email));
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return usuario;
	}


}




