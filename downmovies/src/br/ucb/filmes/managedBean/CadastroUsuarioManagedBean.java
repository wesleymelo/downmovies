package br.ucb.filmes.managedBean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ucb.filmes.beans.Usuario;
import br.ucb.filmes.dao.UsuarioDAO;
import br.ucb.filmes.util.FacesUtil;
import br.ucb.filmes.validator.ValidaSenha;



@ManagedBean 
@ViewScoped
public class CadastroUsuarioManagedBean  implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nomeUsuario;
	private String sobrenome;
	private String email;
	private String senha;
	private Usuario usuario;
	private String confirmaSenha;
	

	public void cadastrarUsuario(){
		try {
			UsuarioDAO insert = new UsuarioDAO();
			String erros = ValidaSenha.verificarValidadeSenha(getSenha(), getConfirmaSenha());
			if(!erros.isEmpty())
				FacesUtil.mensErro(erros);
			insert.insert(atribuicaoUsuario());
			FacesUtil.mensInfo("Usuario cadastrado com Sucesso");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private Usuario atribuicaoUsuario() {
		usuario = new Usuario();
		usuario.setNome(nomeUsuario);
		usuario.setSobrenome(sobrenome);
		usuario.setEmail(email);
		usuario.setSenha(senha);
		return usuario;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}
	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
