package br.ucb.filmes.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table
public class Filme implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id_filme;
	
	@Column
	private String titulo;

	@Column
	private String diretor;
	
	@Column
	private Integer ano_lancamento;
	
	@Column
	private String idioma;
	
	@Column
	private String legenda;
	
	@Column
	private Double tamanho;
	
	@Column
	private Integer tempo_duracao;


	public Integer getId_filme() {
		return id_filme;
	}
	public void setId_filme(Integer id_filme) {
		this.id_filme = id_filme;
	}

	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDiretor() {
		return diretor;
	}
	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public String getLegenda() {
		return legenda;
	}
	public void setLegenda(String legenda) {
		this.legenda = legenda;
	}
	public Double getTamanho() {
		return tamanho;
	}
	public void setTamanho(Double tamanho) {
		this.tamanho = tamanho;
	}

	public Integer getAno_lancamento() {
		return ano_lancamento;
	}

	public void setAno_lancamento(Integer ano_lancamento) {
		this.ano_lancamento = ano_lancamento;
	}

	public Integer getTempo_duracao() {
		return tempo_duracao;
	}

	public void setTempo_duracao(Integer tempo_duracao) {
		this.tempo_duracao = tempo_duracao;
	}

	
}
