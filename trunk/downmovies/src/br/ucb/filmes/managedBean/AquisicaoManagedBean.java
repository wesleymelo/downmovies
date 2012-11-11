package br.ucb.filmes.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.ucb.filmes.beans.Aquisicao;
import br.ucb.filmes.dao.AquisicaoDAO;

@ManagedBean
@SessionScoped
public class AquisicaoManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Aquisicao> aquisicoes;
	private List<Aquisicao> filteredAquisicoes;
	private Aquisicao aquisicao;
	private AquisicaoDAO dao;
	
	public AquisicaoManagedBean() {
		dao = new AquisicaoDAO();
		aquisicoes = dao.recoveryAll();
	}
	
	public List<Aquisicao> getAquisicoes() {
		return aquisicoes;
	}

	public List<Aquisicao> getFilteredAquisicoes() {
		return filteredAquisicoes;
	}

	public void setFilteredAquisicoes(List<Aquisicao> filteredAquisicoes) {
		this.filteredAquisicoes = filteredAquisicoes;
	}

	public void setAquisicoes(List<Aquisicao> aquisicoes) {
		this.aquisicoes = aquisicoes;
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
