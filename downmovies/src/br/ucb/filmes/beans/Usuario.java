package br.ucb.filmes.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

@Entity
@Table
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String email;
	
	@Column
	private String nome;
	
	@Column	
	private String sobrenome; 
	
	@Column
	private String senha;
	
	
    @SuppressWarnings("deprecation")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "aquisicaoPK.usuario",      cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE,   org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	private List<Aquisicao> aquisicoes = new ArrayList<Aquisicao>();
	
	
	@ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	@JoinTable(name="autorizacao",
			joinColumns={
				@JoinColumn(name="email")				
			},
			inverseJoinColumns={
				@JoinColumn(name="idPerfil")
			}		
	)
	
	
	private List<Perfil> perfis;   
	//private Map<Integer, Perfil> perfis;
	
	/*public Map<Integer, Perfil> getPerfis() {
		return perfis;
	}
	
	public void setPerfis(Perfil perfil) {
		
	}

	public void setPerfis(Map<Integer, Perfil> perfis) {
		this.perfis = perfis;
	}*/
	

	@Transient
	private Perfil perfil;

	public List<Aquisicao> getAquisicoes() {
		return aquisicoes;
	}

	public void setAquisicoes(List<Aquisicao> aquisicoes) {
		this.aquisicoes = aquisicoes;
	}

	public Perfil getPerfil() {
		if(perfis != null)
			return perfis.get(0);
		return null;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
		if(perfis == null)
			perfis = new ArrayList<Perfil>();
		perfis.add(this.perfil);
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
	
	public String getFullName(){
		return (nome+" "+sobrenome).toUpperCase();
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [email=" + email + "]";
	}

	
	
	
	
}
