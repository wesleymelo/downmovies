package br.ucb.filmes.beans;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Embeddable
public class AquisicaoPK implements Serializable {

	private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Usuario usuario;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Filme filme;
    
    public AquisicaoPK() {}

	public AquisicaoPK(String email, Integer idFilme) {
		super();
	}

	
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
