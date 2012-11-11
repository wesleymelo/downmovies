package br.ucb.filmes.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Aquisicao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	

	@EmbeddedId
	private AquisicaoPK aquisicaoPK;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	public Aquisicao() {
		aquisicaoPK = new AquisicaoPK();
		data = new Date();
	}
		
	public Aquisicao(AquisicaoPK aquisicaoPK, Date data) {
		this.aquisicaoPK = aquisicaoPK;
		this.data = data;
	}

	public AquisicaoPK getAquisicaoPK() {
		return aquisicaoPK;
	}

	public void setAquisicaoPK(AquisicaoPK aquisicaoPK) {
		this.aquisicaoPK = aquisicaoPK;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
