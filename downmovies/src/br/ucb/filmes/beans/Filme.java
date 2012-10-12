package br.ucb.filmes.beans;

import java.io.Serializable;

public class Filme implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String formato;
	private Categoria categoria;
	private String titulo;
	private String descricao;
	private String diretor;
	private Integer anoLancamento;
	private String idioma;
	private String legenda;
	private String qualidade;
	private Double tamanho;
	private Integer tempoDuracao;
	
	public Filme(Integer id, String formato, Categoria categoria,
			String titulo, String descricao, String diretor,
			Integer anoLancamento, String idioma, String legenda,
			String qualidade, Double tamanho, Integer tempoDuracao) {
		super();
		this.id = id;
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
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
