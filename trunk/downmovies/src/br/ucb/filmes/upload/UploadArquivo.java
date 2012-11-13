package br.ucb.filmes.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;

import org.primefaces.model.UploadedFile;

public class UploadArquivo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private byte [] filmeArq;
	private byte [] imagemArq;

	private static final String DRIVER = "C:/Users/Daniel Neto/Desenvolvimento/WorkSpace";
	private UploadedFile arquivoFilme;
	private UploadedFile arquivoImagem;
	private static final  String CAMINHO = DRIVER+"/downmovies/WebContent/views/filmes/";
	private String nomeArquivo = null;
	private String extensaoImg;
	
	public void fileUploadActionTorrent(UploadedFile  event) {
		
		//String caminho = FacesContext.getCurrentInstance().getCurrentInstance().getExternalContext().getRequestContextPath();
		//System.out.println(CAMINHO);
		arquivoFilme = event;
		filmeArq = arquivoFilme.getContents();
		File file = new File(CAMINHO);
		file.mkdirs();
		
	}
	public void fileUploadActionImagem(UploadedFile  event) {
		arquivoImagem = event;
		imagemArq = arquivoImagem.getContents();
		nomeArquivo = event.getFileName();
		extensaoImg = nomeArquivo.substring((nomeArquivo.indexOf(".")+1), nomeArquivo.length());
		File file = new File(CAMINHO);
		file.mkdirs();
	}

	
	@SuppressWarnings("static-access")
	public void gravarFilme(){
		
		try {

			FileOutputStream fos;
			fos = new FileOutputStream(this.CAMINHO+nomeArquivo+".torrent");
			fos.write(filmeArq);
			fos.flush();
			fos.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	@SuppressWarnings("static-access")
	public void gravarImagem(){
		
		try {
			FileOutputStream fos;
			fos = new FileOutputStream(this.CAMINHO+nomeArquivo+"."+extensaoImg);
			fos.write(imagemArq);
			fos.flush();
			fos.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	public byte[] getFilmeArq() {
		return filmeArq;
	}
	public void setFilmeArq(byte[] filmeArq) {
		this.filmeArq = filmeArq;
	}
	public byte[] getImagemArq() {
		return imagemArq;
	}
	public void setImagemArq(byte[] imagemArq) {
		this.imagemArq = imagemArq;
	}
	public UploadedFile getArquivoFilme() {
		return arquivoFilme;
	}
	public void setArquivoFilme(UploadedFile arquivoFilme) {
		this.arquivoFilme = arquivoFilme;
	}
	public UploadedFile getArquivoImagem() {
		return arquivoImagem;
	}
	public void setArquivoImagem(UploadedFile arquivoImagem) {
		this.arquivoImagem = arquivoImagem;
	}
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
	public String getExtensaoImg() {
		return extensaoImg;
	}
	public void setExtensaoImg(String extensaoImg) {
		this.extensaoImg = extensaoImg;
	}
	public static String getCaminho() {
		return CAMINHO;
	}
	
}
