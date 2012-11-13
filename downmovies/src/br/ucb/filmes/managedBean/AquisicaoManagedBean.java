package br.ucb.filmes.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.StreamedContent;

import br.ucb.filmes.beans.Aquisicao;
import br.ucb.filmes.beans.Filme;
import br.ucb.filmes.dao.AquisicaoDAO;
import br.ucb.filmes.util.FileUtil;

@ManagedBean
@RequestScoped
public class AquisicaoManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String DRIVE = "C:/Users/Daniel Neto/Desenvolvimento/WorkSpace";

	private List<Aquisicao> filteredAquisicoes;
	private Aquisicao aquisicao;
	private AquisicaoDAO dao;
	
	public AquisicaoManagedBean() {
		dao = new AquisicaoDAO();
	}
	
	public List<Aquisicao> getAquisicoes() {
		return dao.recoveryAll();
	}

	public List<Aquisicao> getFilteredAquisicoes() {
		return filteredAquisicoes;
	}

	public void setFilteredAquisicoes(List<Aquisicao> filteredAquisicoes) {
		this.filteredAquisicoes = filteredAquisicoes;
	}

	public Aquisicao getAquisicao() {
		return aquisicao;
	}

	public void setAquisicao(Aquisicao aquisicao) {
		this.aquisicao = aquisicao;
	}
	
	public StreamedContent gerarRelatorioPDF() {
		FileUtil fileUtil = new FileUtil();
		try {
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("DIRETORIO_IMAGENS",DRIVE+"/downmovies/WebContent/views/images/");
			String caminho = "DRIVE/downmovies/WebContent/resources/jasper";
			String nomeArquivo = "AQUISICAO";			
			fileUtil.gerarArquivoDownloadPDF(parametros, "application/pdf", "aquisicao.jasper", caminho,
					nomeArquivo, listaAquisicoes());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileUtil.getStreamedfile();
	}
	private List<Filme> listaAquisicoes(){
		List<Filme> aquisicoes = new ArrayList<Filme>();
		for (Aquisicao aq : getAquisicoesByUser()) {
			aquisicoes.add(aq.getAquisicaoPK().getFilme());
		}
		return aquisicoes;
	}
	public List<Aquisicao> getAquisicoesByUser(){
		String email = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
		return dao.recoveryByUsuario(email);		
		
	}
	
}
