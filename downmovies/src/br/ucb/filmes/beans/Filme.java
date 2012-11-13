package br.ucb.filmes.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import br.ucb.filmes.enums.EnumFormato;
import br.ucb.filmes.enums.EnumIdioma;
import br.ucb.filmes.enums.EnumQualidade;


@Entity
@Table
public class Filme implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer idFilme;
	
	@Column
	private String titulo;

	@Column
	private String diretor;
	
	@Column
	private Integer anoLancamento;

	@Column
	private Integer tempoDuracao;
	
	@ManyToOne
	@JoinColumn(name="idCategoria")
	private Categoria categoria;
	
	@Column
	private Integer formato;
	
	@Column
	private Integer qualidade;
	
	@Column
	private Integer idioma;
	
	@Column
	private Integer legenda;
	
	@Column
	private Integer tamanho;
	
	@Column
	private String descricao;
	
	@Column
	private String extensaoImg;
	
	@SuppressWarnings("deprecation")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "aquisicaoPK.filme",      cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE,   org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	private List<Aquisicao> aquisicoes = new ArrayList<Aquisicao>();
	
	public List<Aquisicao> getAquisicoes() {
		return aquisicoes;
	}
	public void setAquisicoes(List<Aquisicao> aquisicoes) {
		this.aquisicoes = aquisicoes;
	}
	public Integer getTamanho() {
		return tamanho;
	}
	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}
	public String getExtensaoImg() {
		return extensaoImg;
	}
	
	public void setExtensaoImg(String extensaoImg) {
		this.extensaoImg = extensaoImg;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Integer getFormato() {
		return formato;
	}
	public void setFormato(Integer formato) {
		this.formato = formato;
	}
	public Integer getQualidade() {
		return qualidade;
	}
	public void setQualidade(Integer qualidade) {
		this.qualidade = qualidade;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Integer getIdFilme() {
		return idFilme;
	}
	public void setIdFilme(Integer idFilme) {
		this.idFilme = idFilme;
	}
	public Integer getAnoLancamento() {
		return anoLancamento;
	}
	public void setAnoLancamento(Integer anoLancamento) {
		this.anoLancamento = anoLancamento;
	}
	public Integer getTempoDuracao() {
		return tempoDuracao;
	}
	public void setTempoDuracao(Integer tempoDuracao) {
		this.tempoDuracao = tempoDuracao;
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

	public Integer getIdioma() {
		return idioma;
	}
	public void setIdioma(Integer idioma) {
		this.idioma = idioma;
	}
	public Integer getLegenda() {
		return legenda;
	}
	public void setLegenda(Integer legenda) {
		this.legenda = legenda;
	}
	
	public String getDescricaoFormato(){
		if(formato != null)
			return EnumFormato.findByFormat((int)formato).getNome();
		return null;
		
	}
	
	public String getDescricaoLegenda(){
		if(legenda != null)
			return EnumIdioma.findByValue((int)legenda).getNome();
		return null;
		
	}
	
	public String getDescricaoIdioma(){
		if(idioma != null)
			return EnumIdioma.findByValue((int)idioma).getNome();
		return null;
		
	}
	
	public String getDescricaoQualidade(){
		if(qualidade != null)
			return EnumQualidade.findByQuality((int)qualidade).getNome();
		return null;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((anoLancamento == null) ? 0 : anoLancamento.hashCode());
		result = prime * result
				+ ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((diretor == null) ? 0 : diretor.hashCode());
		result = prime * result
				+ ((extensaoImg == null) ? 0 : extensaoImg.hashCode());
		result = prime * result + ((formato == null) ? 0 : formato.hashCode());
		result = prime * result + ((idFilme == null) ? 0 : idFilme.hashCode());
		result = prime * result + ((idioma == null) ? 0 : idioma.hashCode());
		result = prime * result + ((legenda == null) ? 0 : legenda.hashCode());
		result = prime * result
				+ ((qualidade == null) ? 0 : qualidade.hashCode());
		result = prime * result + ((tamanho == null) ? 0 : tamanho.hashCode());
		result = prime * result
				+ ((tempoDuracao == null) ? 0 : tempoDuracao.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		Filme other = (Filme) obj;
		if (anoLancamento == null) {
			if (other.anoLancamento != null)
				return false;
		} else if (!anoLancamento.equals(other.anoLancamento))
			return false;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (diretor == null) {
			if (other.diretor != null)
				return false;
		} else if (!diretor.equals(other.diretor))
			return false;
		if (extensaoImg == null) {
			if (other.extensaoImg != null)
				return false;
		} else if (!extensaoImg.equals(other.extensaoImg))
			return false;
		if (formato == null) {
			if (other.formato != null)
				return false;
		} else if (!formato.equals(other.formato))
			return false;
		if (idFilme == null) {
			if (other.idFilme != null)
				return false;
		} else if (!idFilme.equals(other.idFilme))
			return false;
		if (idioma == null) {
			if (other.idioma != null)
				return false;
		} else if (!idioma.equals(other.idioma))
			return false;
		if (legenda == null) {
			if (other.legenda != null)
				return false;
		} else if (!legenda.equals(other.legenda))
			return false;
		if (qualidade == null) {
			if (other.qualidade != null)
				return false;
		} else if (!qualidade.equals(other.qualidade))
			return false;
		if (tamanho == null) {
			if (other.tamanho != null)
				return false;
		} else if (!tamanho.equals(other.tamanho))
			return false;
		if (tempoDuracao == null) {
			if (other.tempoDuracao != null)
				return false;
		} else if (!tempoDuracao.equals(other.tempoDuracao))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Filme [idFilme=" + idFilme + ", titulo=" + titulo
				+ ", diretor=" + diretor + ", anoLancamento=" + anoLancamento
				+ ", tempoDuracao=" + tempoDuracao + ", categoria=" + categoria
				+ ", formato=" + formato + ", qualidade=" + qualidade
				+ ", idioma=" + idioma + ", legenda=" + legenda + ", tamanho="
				+ tamanho + ", descricao=" + descricao + ", extensaoImg="
				+ extensaoImg + "]";
	}
	
	

}
