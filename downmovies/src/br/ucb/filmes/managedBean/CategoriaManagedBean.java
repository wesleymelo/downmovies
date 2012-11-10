package br.ucb.filmes.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

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
	private MenuModel menu;
	private CategoriaDAO dao;

	public CategoriaManagedBean() {
		dao = new CategoriaDAO();
		this.categorias = dao.recoveryAll();
		menu = new DefaultMenuModel();

		MenuItem item;


		menu = new DefaultMenuModel();


		Submenu submenu = new Submenu();
		submenu.setLabel("Categorias");


		for (Categoria c : this.categorias) {
			if(!c.getDescricao().equalsIgnoreCase("Nao Disponivel")){
				item = new MenuItem();
				item.setValue(c.getDescricao());
				item.setUrl("#");
				item.setOnclick("filtrar");
				submenu.getChildren().add(item);
			}
		}

		menu.addSubmenu(submenu);
	}

	public MenuModel getMenu() {
		return menu;
	}

	
	public void filtrar(){
		System.out.println("jaskdhjskdfh sdgfj sdfh ghdghh jack");
	}


	public void setMenu(MenuModel menu) {
		this.menu = menu;
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
