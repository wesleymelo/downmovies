package br.ucb.filmes.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class Qualidade {
	
	@Id
	@GeneratedValue
	private Integer id_qualidade;
	
	@Column
	private String descricao;

	public Integer getId_qualidade() {
		return id_qualidade;
	}

	public void setId_qualidade(Integer id_qualidade) {
		this.id_qualidade = id_qualidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
