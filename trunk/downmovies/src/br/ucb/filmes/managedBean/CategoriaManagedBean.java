package br.ucb.filmes.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;


import br.ucb.filmes.beans.Categoria;
import br.ucb.fimes.daos.CategoriaDAO;


@ManagedBean
@SessionScoped
public class CategoriaManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Categoria categoria = new Categoria();
	private List<Categoria> categorias = new ArrayList<Categoria>();
	private List<Categoria> filteredCategorias; 
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public List<Categoria> getFilteredCategorias() {
		return filteredCategorias;
	}

	public void setFilteredCategorias(List<Categoria> filteredCategorias) {
		this.filteredCategorias = filteredCategorias;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public String insert(){
		FacesMessage msg = new FacesMessage();
		
		CategoriaDAO dao = new CategoriaDAO();
		if(dao.insert(getCategoria()) == 0)
			msg.setSummary("Não foi possível inserir a categoria.");
		else
			msg.setSummary("Categoria adicionado com sucesso.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		setCategorias(dao.recoverAll());
		return "categoriaList";
	}
	
	public String cadastrar(){
		return "categoriaForm";
	}
	
	public void onEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Categoria Editada", ((Categoria) event.getObject()).getDescricao());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Categoria Cancelada", ((Categoria) event.getObject()).getDescricao());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
}
