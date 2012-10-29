package br.ucb.filmes.managedBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;
import org.primefaces.model.UploadedFile;

import br.ucb.filmes.beans.Filme;
import br.ucb.filmes.dao.FilmeDAO;
import br.ucb.filmes.enums.EnumFormato;
import br.ucb.filmes.enums.EnumQualidade;
import br.ucb.filmes.upload.UploadArquivo;
import br.ucb.filmes.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FilmeManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(FilmeManagedBean.class);
	private Filme filme;
	private List<Filme> filmes;
	private UploadArquivo uploadArquivo;
	private UploadedFile arqFilme; 
	private UploadedFile arqImagem; 
 	public FilmeManagedBean() {
 	    filmes = new FilmeDAO().recoveryAll();
		filme = new Filme();
		uploadArquivo = new UploadArquivo();
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
			
			uploadArquivo.fileUploadActionTorrent(arqFilme);
			uploadArquivo.fileUploadActionImagem(arqImagem);
			filme.setExtensaoImg(uploadArquivo.getExtensaoImg());
			System.out.println("Extensão: "+filme.getExtensaoImg());
			
			dao.insert(filme);
			uploadArquivo.setNomeArquivo(filme.getIdFilme().toString());
			uploadArquivo.gravarFilme();
			uploadArquivo.gravarImagem();
			filmes = dao.recoveryAll();
			
			FacesUtil.mensInfo("Filme cadastrado com Sucesso");
			log.info("Filme cadastrado com Sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			FacesUtil.mensErro("Erro ao cadastrar filme");
		}
		System.out.println("\\o/ AAQUI OH");
		return "filmes";
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
