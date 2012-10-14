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
	private Integer id;
	
	@Column
	private String descricao;
	
	public Categoria() {
		// TODO Auto-generated constructor stub
	}
	
	public Categoria(int id, String descricao) {
		setId(id);
		setDescricao(descricao);
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
