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
	private String formato;
	
	@ManyToOne
	@JoinColumn(name="idCategoria")
	private Categoria categoria;
	
	@Column
	private String titulo;
	
	@Column
	private String descricao;
	
	@Column
	private String diretor;
	
	@Column
	private Integer anoLancamento;
	
	@Column
	private String idioma;
	
	@Column
	private String legenda;
	
	@Column
	private String qualidade;
	
	@Column
	private Double tamanho;
	
	@Column
	private Integer tempoDuracao;
	
	public Filme(Integer id_filme, String formato, Categoria categoria,
			String titulo, String descricao, String diretor,
			Integer anoLancamento, String idioma, String legenda,
			String qualidade, Double tamanho, Integer tempoDuracao) {
		super();
		this.id_filme = id_filme;
		this.formato = formato;
		this.categoria = categoria;
		this.titulo = titulo;
		this.descricao = descricao;
		this.diretor = diretor;
		this.anoLancamento = anoLancamento;
		this.idioma = idioma;
		this.legenda = legenda;
		this.qualidade = qualidade;
		this.tamanho = tamanho;
		this.tempoDuracao = tempoDuracao;
	}
	public Filme() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getId_filme() {
		return id_filme;
	}
	public void setId_filme(Integer id_filme) {
		this.id_filme = id_filme;
	}
	public String getFormato() {
		return formato;
	}


	public void setFormato(String formato) {
		this.formato = formato;
	}


	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDiretor() {
		return diretor;
	}
	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}
	
	public Integer getAnoLancamento() {
		return anoLancamento;
	}


	public void setAnoLancamento(Integer anoLancamento) {
		this.anoLancamento = anoLancamento;
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
	public String getQualidade() {
		return qualidade;
	}
	public void setQualidade(String qualidade) {
		this.qualidade = qualidade;
	}
	public Double getTamanho() {
		return tamanho;
	}
	public void setTamanho(Double tamanho) {
		this.tamanho = tamanho;
	}
	public Integer getTempoDuracao() {
		return tempoDuracao;
	}
	public void setTempoDuracao(Integer tempoDuracao) {
		this.tempoDuracao = tempoDuracao;
	}
	
	
	
}
