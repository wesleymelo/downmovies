package br.ucb.filmes.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.ucb.filmes.beans.Perfil;
import br.ucb.filmes.dao.PerfilDAO;

@ManagedBean
@RequestScoped
public class PerfilManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private List<Perfil> perfis;
	
	public PerfilManagedBean() {
		perfis = new PerfilDAO().recoveryAll();
		for(Perfil p : perfis)
			System.out.println(p);
	}
	
	public List<Perfil> getPerfis() {
		return perfis;
	}
	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

}
