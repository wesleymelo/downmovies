package br.ucb.filmes.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
@AssociationOverrides({ @AssociationOverride(name = "aquisicaoPK.usuario", joinColumns = @JoinColumn(name = "email")),
@AssociationOverride(name = "aquisicaoPK.filme", joinColumns = @JoinColumn(name = "idFilme")) })
public class Aquisicao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	

	@EmbeddedId
	private AquisicaoPK aquisicaoPK = new AquisicaoPK();
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	public Aquisicao() {
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
