package br.ucb.filmes.managedBean;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.ucb.filmes.beans.Aquisicao;
import br.ucb.filmes.beans.Filme;
import br.ucb.filmes.dao.AquisicaoDAO;
import br.ucb.filmes.dao.FilmeDAO;
import br.ucb.filmes.dao.UsuarioDAO;
import br.ucb.filmes.enums.EnumFormato;
import br.ucb.filmes.enums.EnumIdioma;
import br.ucb.filmes.enums.EnumQualidade;
import br.ucb.filmes.upload.UploadArquivo;
import br.ucb.filmes.util.FacesUtil;
import br.ucb.filmes.util.FileUtil;

@ManagedBean
@SessionScoped
public class FilmeManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(FilmeManagedBean.class);
	private static final Logger log2 = Logger.getLogger(AquisicaoManagedBean.class);
	private Filme filme;
	private List<Filme> filmes;
	private List<Filme> filmesCategoria;
	private List<Filme> filteredFilmes;
	private UploadArquivo uploadArquivo;
	private UploadedFile arqFilme; 
	private UploadedFile arqImagem;
	private FilmeDAO dao;
	private boolean mostraFilmes;
 	public FilmeManagedBean() {
 		dao = new FilmeDAO();
 	    filmes = dao.recoveryAll();
		filme = new Filme();
		uploadArquivo = new UploadArquivo();
	}
	
	public List<Filme> getFilteredFilmes() {
		return filteredFilmes;
	}


	public void buscCategoriaId(Integer categoria) {
 	    filmesCategoria = new ArrayList<Filme>();
		for (Filme filmeTemp : filmes) {
			if(filmeTemp.getCategoria().getIdCategoria() == categoria){
				filmesCategoria.add(filmeTemp);
			}
		}
		mostraFilmes = true;
	}
	
	public void limpaFiltro() {
 	    mostraFilmes = false;
	}

	public List<Filme> getFilmesCategoria() {

		return filmesCategoria;
	}

	public void setFilmesCategoria(List<Filme> filmesCategoria) {
		this.filmesCategoria = filmesCategoria;
	}

	public void setFilteredFilmes(List<Filme> filteredFilmes) {
		this.filteredFilmes = filteredFilmes;
	}

	public List<EnumIdioma> getIdiomas() {
		return EnumIdioma.getIdiomas();
	}

	public boolean isMostraFilmes() {
		return mostraFilmes;
	}

	public void setMostraFilmes(boolean mostraFilmes) {
		this.mostraFilmes = mostraFilmes;
	}

	public Map<String, Integer> getMapIdioma() {
		return EnumIdioma.getMapaIdioma();
	}

	public Map<String, Integer> getMapFormato() {
		return EnumFormato.getMapaFomato();
	}


	public  Map<String, Integer> getMapQualidade() {
		return EnumQualidade.getMapaQualidade();
	}
	public StreamedContent download(String arquivo,String nomeFilme){  

		FileUtil file = null;
		try {
	    	file = new FileUtil();
			
	    	file.downloadFile(UploadArquivo.getCaminho(),arquivo, nomeFilme+".torrent");
			
			AquisicaoDAO dao = new AquisicaoDAO();
			Aquisicao aquisicao = new Aquisicao();
			aquisicao.getAquisicaoPK().setFilme(new FilmeDAO().consult(new Integer(arquivo)));
			aquisicao.getAquisicaoPK().setUsuario(new UsuarioDAO().consult(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName()));
			dao.insert(aquisicao);
			log2.info("Acquisition realized successfully");
			FacesUtil.mensInfo("Download realized successfully");
			log.info("Download realized successfully");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			FacesUtil.mensErro("Could not be downloaded");
			log.error(e.getMessage(), e);
		}catch (Exception e) {
			log2.error(e.getMessage(), e);
		}
	      
	    return file.getStreamedfile();
	}
	
	public String salvar()	{

		try {
			
			if(arqFilme != null)uploadArquivo.fileUploadActionTorrent(arqFilme);
			if(arqImagem != null )uploadArquivo.fileUploadActionImagem(arqImagem);
			if(arqImagem != null )filme.setExtensaoImg(uploadArquivo.getExtensaoImg());
						
			dao.insert(this.filme);
			
			if(arqFilme != null || arqImagem != null )uploadArquivo.setNomeArquivo(filme.getIdFilme().toString());
			if(arqFilme != null)uploadArquivo.gravarFilme();
			if(arqImagem != null )uploadArquivo.gravarImagem();
			filmes = dao.recoveryAll();

			FacesUtil.mensInfo("Record registered successfully");
			log.info("Record registered successfully");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			FacesUtil.mensErro("Unable to register the record");
		}
		init();
		return "filmes";
	}
	
	public void excluir(){
		
		try {
			dao.delete(filme);
			FacesUtil.mensInfo("Record deleted successfully");	
			log.info("Record deleted successfully");
			init();
			filmes = dao.recoveryAll();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			FacesUtil.mensErro("Unable to delete the record");
		}
	}
	
	public void init() {
		filme = new Filme();
	}
	
	public String novoFilme(){
		init();
		return "filme.jsf?faces-redirect=true";
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
