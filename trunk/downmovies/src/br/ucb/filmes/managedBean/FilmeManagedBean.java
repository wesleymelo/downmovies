package br.ucb.filmes.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.ucb.filmes.beans.Categoria;
import br.ucb.filmes.beans.Filme;
import br.ucb.filmes.dao.CategoriaDAO;
import br.ucb.filmes.dao.FilmeDAO;

@ManagedBean
public class FilmeManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Filme filme;
	private List<Filme> filmes;
	private List<Categoria> categorias;

	public FilmeManagedBean() {
		this.filme = new Filme();
		this.categorias = new CategoriaDAO().recoveryAll();
	} 
	
	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
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
		
		dao.insert(getFilme());
		
		System.out.println(filme);
		
		msg.setSummary("Filme inserido com sucesso");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		setFilmes(dao.recoveryAll());
		return "filmeList";
	}
	
	public String cadastrar(){
		return "filmeForm";
	}
	
}