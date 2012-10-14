package br.ucb.filmes.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class Categoria {
	
	@Id
	@GeneratedValue
	private Integer id_categoria;
	
	@Column
	private String descricao;
	
	public Categoria() {
		// TODO Auto-generated constructor stub
	}
	
	public Categoria(int id_categoria, String descricao) {
		setId_categoria(id_categoria);
		setDescricao(descricao);
	}
	
	public Integer getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(Integer id_categoria) {
		this.id_categoria = id_categoria;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
