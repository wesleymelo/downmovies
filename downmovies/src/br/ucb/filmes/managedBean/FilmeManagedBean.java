package br.ucb.filmes.managedBean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.primefaces.model.UploadedFile;

import br.ucb.filmes.beans.Filme;
import br.ucb.filmes.dao.FilmeDAO;
import br.ucb.filmes.enums.EnumFormato;
import br.ucb.filmes.enums.EnumQualidade;
import br.ucb.filmes.upload.UploadArquivo;
import br.ucb.filmes.util.FacesUtil;

@ManagedBean
@RequestScoped
public class FilmeManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(FilmeManagedBean.class);
	private Filme filme;
	private List<Filme> filmes;
	private List<Filme> filteredFilmes;
	private UploadArquivo uploadArquivo;
	private UploadedFile arqFilme; 
	private UploadedFile arqImagem; 
 	public FilmeManagedBean() {
 	    filmes = new FilmeDAO().recoveryAll();
		filme = new Filme();
		uploadArquivo = new UploadArquivo();
	}
	
	public List<Filme> getFilteredFilmes() {
		return filteredFilmes;
	}



	public void setFilteredFilmes(List<Filme> filteredFilmes) {
		this.filteredFilmes = filteredFilmes;
	}



	public Map<String, Integer> getMapFormato() {
		return EnumFormato.getMapaFomato();
	}


	public  Map<String, Integer> getMapQualidade() {
		return EnumQualidade.getMapaQualidade();
	}
	
	
	public String cadastraFilme()	{

		try {
			
			FilmeDAO dao = new FilmeDAO();
			
			if(arqFilme != null)uploadArquivo.fileUploadActionTorrent(arqFilme);
			if(arqImagem != null )uploadArquivo.fileUploadActionImagem(arqImagem);
			if(arqImagem != null )filme.setExtensaoImg(uploadArquivo.getExtensaoImg());
			
			dao.insert(filme);
			
			if(arqFilme != null || arqImagem != null )uploadArquivo.setNomeArquivo(filme.getIdFilme().toString());
			if(arqFilme != null)uploadArquivo.gravarFilme();
			if(arqImagem != null )uploadArquivo.gravarImagem();
			filmes = dao.recoveryAll();
			
			FacesUtil.mensInfo("Filme cadastrado com Sucesso");
			log.info("Filme cadastrado com Sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			FacesUtil.mensErro("Erro ao cadastrar filme");
		}
		return "filmeList";
	}

	
	public void excluir(ActionEvent evento) throws SQLException {
		this.filme = (Filme) evento.getComponent().getAttributes().get("filme");
		FilmeDAO dao = new FilmeDAO();
		dao.delete(filme);
		FacesUtil.mensInfo("Filme excluído com sucesso");		
		this.filme = new Filme();
		this.filmes = dao.recoveryAll();
	}
	
	public UploadedFile getArqFilme() {
		return arqFilme;
	}



	public void setArqFilme(UploadedFile arqFilme) {
		this.arqFilme = arqFilme;
	}



	public UploadedFile getArqImagem() {
		return arqImagem;
	}



	public void setArqImagem(UploadedFile arqImagem) {
		this.arqImagem = arqImagem;
	}



	public List<Filme> getFilmes() {
		return filmes;
	}
	
	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}
	
	public Filme getFilme() {
		return filme;
	}
	
	public void setFilme(Filme filme) {
		this.filme = filme;
	}	
	
}
