package br.ucb.filmes.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class Perfil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer idPerfil;
	
	@Column
	private String descricao;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="autorizacao",
			joinColumns={@JoinColumn(name="idPerfil")},
			inverseJoinColumns={@JoinColumn(name="idUsuario")}		
	)
	private List<Usuario> usuarios;
	//private Map<Integer, Usuario> usuarios;
	
	/*public Map<Integer, Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(Map<Integer, Usuario> usuarios) {
		this.usuarios = usuarios;
	}*/
	public Integer getIdPerfil() {
		return idPerfil;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((idPerfil == null) ? 0 : idPerfil.hashCode());
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
		Perfil other = (Perfil) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (idPerfil == null) {
			if (other.idPerfil != null)
				return false;
		} else if (!idPerfil.equals(other.idPerfil))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Perfil [idPerfil=" + idPerfil + ", descricao=" + descricao
				+ ", usuarios=" + usuarios + "]";
	}
	
	
	

}
