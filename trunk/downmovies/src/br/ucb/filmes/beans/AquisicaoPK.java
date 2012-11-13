package br.ucb.filmes.beans;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class AquisicaoPK implements Serializable {

	private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name="email")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name="idFilme")
    private Filme filme;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}
}
