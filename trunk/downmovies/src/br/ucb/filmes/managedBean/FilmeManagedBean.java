package br.ucb.filmes.managedBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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


	private Filme filme;
	private List<Filme> filmes;
	private UploadArquivo uploadArquivo;
	private UploadedFile arqFilme; 
	private UploadedFile arqImagem; 
 	public FilmeManagedBean() {
 	
		filme = new Filme();
		uploadArquivo = new UploadArquivo();
	}
	

	
	public Map<String, Integer> getMapFormato() {
		return EnumFormato.getMapaFomato();
	}


	public  Map<String, Integer> getMapQualidade() {
		return EnumQualidade.getMapaQualidade();
	}
	
	
	public void cadastraFilme()	{

		try {

			
			
			FilmeDAO insert = new FilmeDAO();
			
			insert.insert(filme);
			uploadArquivo.fileUploadActionTorrent(arqFilme);
			uploadArquivo.fileUploadActionImagem(arqImagem);
			uploadArquivo.gravarFilme();
			uploadArquivo.gravarImagem();
			
			FacesUtil.mensInfo("Filme cadastrado com Sucesso");
			
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.mensErro("Erro ao cadastrar filme");
		}
		
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
