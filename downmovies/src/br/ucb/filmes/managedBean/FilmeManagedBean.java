package br.ucb.filmes.managedBean;

import java.io.FileNotFoundException;
import java.io.Serializable;
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
	private Filme filme;
	private List<Filme> filmes;
	private List<Filme> filteredFilmes;
	private UploadArquivo uploadArquivo;
	private UploadedFile arqFilme; 
	private UploadedFile arqImagem;
	private FilmeDAO dao;
	
 	public FilmeManagedBean() {
 		dao = new FilmeDAO();
 	    filmes = dao.recoveryAll();
		filme = new Filme();
		uploadArquivo = new UploadArquivo();
	}
	
	public List<Filme> getFilteredFilmes() {
		return filteredFilmes;
	}

	public void setFilteredFilmes(List<Filme> filteredFilmes) {
		this.filteredFilmes = filteredFilmes;
	}

	public List<EnumIdioma> getIdiomas() {
		return EnumIdioma.getIdiomas();
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
			aquisicao.getAquisicaoPK().setIdFilme(new Integer(arquivo));
			aquisicao.getAquisicaoPK().setEmail(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName());
			dao.insert(aquisicao);
			FacesUtil.mensInfo("Download de filme realizado com Sucesso");
			log.info("Download de filme realizado com Sucesso");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	      
	    return file.getStreamedfile();
	}
	
	public String salvar()	{

		try {
			
			if(arqFilme != null)uploadArquivo.fileUploadActionTorrent(arqFilme);
			if(arqImagem != null )uploadArquivo.fileUploadActionImagem(arqImagem);
			if(arqImagem != null )filme.setExtensaoImg(uploadArquivo.getExtensaoImg());
			
			System.out.println("Id: "+filme.getIdFilme());
			
			dao.insert(this.filme);
			
			if(arqFilme != null || arqImagem != null )uploadArquivo.setNomeArquivo(filme.getIdFilme().toString());
			if(arqFilme != null)uploadArquivo.gravarFilme();
			if(arqImagem != null )uploadArquivo.gravarImagem();
			filmes = dao.recoveryAll();
			
			FacesUtil.mensInfo("Filme salvo com Sucesso");
			log.info("Filme salvo com Sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			FacesUtil.mensErro("Erro ao salvar o filme");
		}
		filme = new Filme();
		return "filmes";
	}
	
	public void excluir(){
		dao.delete(filme);
		FacesUtil.mensInfo("Filme excluído com sucesso");		
		filme = new Filme();
		filmes = dao.recoveryAll();
	}
	
	public void init() {
		filme = new Filme();
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
