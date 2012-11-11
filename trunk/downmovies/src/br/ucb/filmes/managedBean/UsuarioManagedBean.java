package br.ucb.filmes.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.ucb.filmes.beans.Usuario;
import br.ucb.filmes.dao.PerfilDAO;
import br.ucb.filmes.dao.UsuarioDAO;
import br.ucb.filmes.util.FacesUtil;
import br.ucb.filmes.validator.ValidaSenha;

@ManagedBean
@SessionScoped
public class UsuarioManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private Usuario usuarioLogado;
	private List<Usuario> usuarios;
	private List<Usuario> filteredUsuarios;
	private String senhaAtual;
	private String confirmaSenha;
	private UsuarioDAO dao;
	private Boolean isAlter;

	public UsuarioManagedBean() {
		dao = new UsuarioDAO();
		usuarios = dao.recoveryAll();
		usuario = new Usuario();
	}
	
	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public UsuarioDAO getDao() {
		return dao;
	}



	public void setDao(UsuarioDAO dao) {
		this.dao = dao;
	}



	public Boolean getIsAlter() {
		return isAlter;
	}



	public Usuario getUsuarioLogado() {
		
		String email = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
		usuarioLogado = dao.consult(email);
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public void setIsAlter(Boolean isAlter) {
		this.isAlter = isAlter;
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
	
	public void salvarUser() {
		try {
			usuario.setPerfil(new PerfilDAO().consult(2));
			
			String erros = ValidaSenha.verificarValidadeSenha(
					usuario.getSenha(), getConfirmaSenha());

			if (!erros.isEmpty()) {
				FacesUtil.mensErro(erros);
				return;
			} else {
				dao.insert(usuario);
				usuarios = dao.recoveryAll();
				FacesUtil.mensInfo("Usuario cadastrado com Sucesso");
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.mensErro("Erro ao cadastrar usuario");
		}
		usuario = new Usuario();
		isAlter = false;
	}
	
	
	public void alteraSenha(){
		try {
			Usuario usuario = dao.consult(usuarioLogado.getEmail());
			
			if(senhaAtual.equals(usuario.getSenha())){
				System.out.println("Senha:" +confirmaSenha);
				usuario.setSenha(confirmaSenha);
				dao.update(usuario);
				FacesUtil.mensInfo("Senha Alterado com sucesso");
				senhaAtual = null;
				confirmaSenha = null;
				return;
			}
			FacesUtil.mensErro("Senha atual n�o confere");
			
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.mensErro("Erro ao alterar senha");
		}
		senhaAtual = null;
		confirmaSenha = null;
	}

	public String salvar() {
		try {
			String erros = ValidaSenha.verificarValidadeSenha(
					usuario.getSenha(), getConfirmaSenha());
			if(isAlter != null && !isAlter){
				if (dao.consult(usuario.getEmail()) != null) {
					FacesUtil.mensErro("Email j� cadastrado");
					return "usuario";
				}
			}

			if (!erros.isEmpty()) {
				FacesUtil.mensErro(erros);
				return null;
			} else {
				dao.insert(usuario);
				usuarios = dao.recoveryAll();
				if(isAlter != null && isAlter)
				   FacesUtil.mensInfo("Usuario alterado com Sucesso");
				else
				   FacesUtil.mensInfo("Usuario cadastrado com Sucesso");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(isAlter != null && isAlter)
				FacesUtil.mensErro("Erro ao alterar usuario");
			else 
				FacesUtil.mensErro("Erro ao cadastrar usuario");
		}
		usuario = new Usuario();
		isAlter = false;
		return "usuarios";
	}

	public String disconnect(){
		FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
        session.invalidate();
		return "../login.jsf?faces-redirect=true";
	}
	
	public void excluir() {
		dao.delete(usuario);
		FacesUtil.mensInfo("Usu�rio exclu�do com sucesso");
		usuario = new Usuario();
		usuarios = dao.recoveryAll();
	}
	
	public void init() {
		usuario = new Usuario();
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
