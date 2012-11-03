package br.ucb.filmes.managedBean;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import br.ucb.filmes.beans.Categoria;
import br.ucb.filmes.dao.CategoriaDAO;

@ManagedBean
@RequestScoped
public class CategoriaManagedBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Categoria categoria;
	private List<Categoria> categorias;
	
	public CategoriaManagedBean() {
		this.categorias = new CategoriaDAO().recoveryAll();
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public List<Categoria> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
}
