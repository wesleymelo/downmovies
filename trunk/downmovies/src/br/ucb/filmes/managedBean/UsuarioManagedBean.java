package br.ucb.filmes.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.ucb.filmes.beans.Usuario;
import br.ucb.filmes.dao.UsuarioDAO;
import br.ucb.filmes.util.FacesUtil;
import br.ucb.filmes.validator.ValidaSenha;

@ManagedBean 
@SessionScoped
public class UsuarioManagedBean  implements Serializable {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<Usuario> filteredUsuarios;
	private String confirmaSenha;
	private UsuarioDAO dao;
		
	public UsuarioManagedBean() {
		dao = new UsuarioDAO();
		usuarios = dao.recoveryAll();
		usuario = new Usuario();	
	}
	
	public List<Usuario> getFilteredUsuarios() {
		return filteredUsuarios;
	}

	public void setFilteredUsuarios(List<Usuario> filteredUsuarios) {
		this.filteredUsuarios = filteredUsuarios;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String cadastrarUsuario(){
		try {
			String erros = ValidaSenha.verificarValidadeSenha(usuario.getSenha(), getConfirmaSenha());
			if(!erros.isEmpty()){
				FacesUtil.mensErro(erros);
				usuario = new Usuario();
				return null;
			}
			else{
				dao.insert(usuario);
				usuarios = dao.recoveryAll();
				FacesUtil.mensInfo("Usuario cadastrado com Sucesso");
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.mensErro("Erro ao cadastrar usuario");
		}
		usuario = new Usuario();
		return "usuarios";
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}
	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
}
