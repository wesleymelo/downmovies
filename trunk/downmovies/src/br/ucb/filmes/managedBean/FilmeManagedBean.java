package br.ucb.filmes.managedBean;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.ucb.filmes.beans.Filme;
import br.ucb.fimes.daos.FilmeDAO;


@ManagedBean
public class FilmeManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Filme filme;
	private List<Filme> filmes;

	public FilmeManagedBean() {
		this.filme = new Filme();
	} 
	
	public List<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}	
	
	public String insert(){
		FacesMessage msg = new FacesMessage();
		
		FilmeDAO dao = new FilmeDAO();
		if(dao.insert(getFilme()) == 0)
			msg.setSummary("Não foi possível inserir o filme.");
		else
			msg.setSummary("Filme adicionado com sucesso.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		setFilmes(dao.recoverAll());
		return "filmesLista";
	}
	
	public String cadastrar(){
		return "filmeForm";
	}
	
}
