package br.ucb.filmes.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AquisicaoPK implements Serializable {

	private static final long serialVersionUID = 1L;

    @Column
    private String email;

    @Column
    private Integer idFilme ;
    
    public AquisicaoPK() {}

	public AquisicaoPK(String email, Integer idFilme) {
		super();
		this.email = email;
		this.idFilme = idFilme;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIdFilme() {
		return idFilme;
	}

	public void setIdFilme(Integer idFilme) {
		this.idFilme = idFilme;
	}
}
