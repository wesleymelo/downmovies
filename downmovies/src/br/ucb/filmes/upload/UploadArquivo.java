package br.ucb.filmes.upload;

import java.io.File;
import java.io.FileOutputStream;

import org.primefaces.model.UploadedFile;

public class UploadArquivo {
	private byte [] filmeArq;
	private byte [] imagemArq;

	private UploadedFile arquivoFilme;
	private UploadedFile arquivoImagem;
	
	private static final  String caminho = "/downmovies/filmes/";
	private String nomeArquivo = null;
	private String extensaoImg;
	
	public void fileUploadActionTorrent(UploadedFile  event) {
		
		//String caminho = FacesContext.getCurrentInstance().getCurrentInstance().getExternalContext().getRequestContextPath();
		//System.out.println(caminho);
		arquivoFilme = event;
		filmeArq = arquivoFilme.getContents();
		File file = new File(caminho);
		file.mkdirs();
		
	}
	public void fileUploadActionImagem(UploadedFile  event) {
		arquivoImagem = event;
		imagemArq = arquivoImagem.getContents();
		nomeArquivo = event.getFileName();
		extensaoImg = nomeArquivo.substring((nomeArquivo.indexOf(".")+1), nomeArquivo.length());
		File file = new File(caminho);
		file.mkdirs();
	}

	
	@SuppressWarnings("static-access")
	public void gravarFilme(){
		
		try {

			FileOutputStream fos;
			fos = new FileOutputStream(this.caminho+nomeArquivo+".torrent");
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
			fos = new FileOutputStream(this.caminho+nomeArquivo+"."+extensaoImg);
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
	public static String getCaminho() {
		return caminho;
	}
	public String getExtensaoImg() {
		return extensaoImg;
	}
	public void setExtensaoImg(String extensaoImg) {
		this.extensaoImg = extensaoImg;
	}
	
	
}
