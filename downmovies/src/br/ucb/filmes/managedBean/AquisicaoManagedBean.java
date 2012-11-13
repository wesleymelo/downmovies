package br.ucb.filmes.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.ucb.filmes.beans.Aquisicao;
import br.ucb.filmes.dao.AquisicaoDAO;

@ManagedBean
@RequestScoped
public class AquisicaoManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Aquisicao> filteredAquisicoes;
	private Aquisicao aquisicao;
	private AquisicaoDAO dao;
	
	public AquisicaoManagedBean() {
		dao = new AquisicaoDAO();
	}
	
	public List<Aquisicao> getAquisicoes() {
		return dao.recoveryAll();
	}

	public List<Aquisicao> getFilteredAquisicoes() {
		return filteredAquisicoes;
	}

	public void setFilteredAquisicoes(List<Aquisicao> filteredAquisicoes) {
		this.filteredAquisicoes = filteredAquisicoes;
	}

	public Aquisicao getAquisicao() {
		return aquisicao;
	}

	public void setAquisicao(Aquisicao aquisicao) {
		this.aquisicao = aquisicao;
	}
	
	public List<Aquisicao> getAquisicoesByUser(){
		String email = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
		return dao.recoveryByUsuario(email);		
		
	}
	
}
