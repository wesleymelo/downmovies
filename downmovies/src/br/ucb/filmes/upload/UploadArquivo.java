package br.ucb.filmes.upload;

import java.io.File;
import java.io.FileOutputStream;


import org.primefaces.model.UploadedFile;

public class UploadArquivo {
	private byte [] filmeArq;
	private byte [] imagemArq;

	private UploadedFile arquivoFilme;
	private UploadedFile arquivoImagem;
	private static final  String caminho = "C:/filmes/arquivoFilme/";
	private String nomeCaminhoFilme;
	private String nomeCaminhoImagem;
	
	

	public void fileUploadActionTorrent(UploadedFile  event) {
		arquivoFilme = event;
		nomeCaminhoFilme =  caminho+arquivoFilme.getFileName();
		filmeArq = arquivoFilme.getContents();
		File file = new File(caminho);
		file.mkdirs();
		
	}
	public void fileUploadActionImagem(UploadedFile  event) {
		arquivoImagem = event;
		nomeCaminhoImagem = caminho+ arquivoImagem.getFileName();
		imagemArq = arquivoImagem.getContents();
		File file = new File(caminho);
		file.mkdirs();
	}

	
	public void gravarFilme(){
		
		try {

			FileOutputStream fos;
			fos = new FileOutputStream(this.nomeCaminhoFilme);
			fos.write(filmeArq);
			fos.flush();
			fos.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	public void gravarImagem(){
		
		try {
			FileOutputStream fos;
			fos = new FileOutputStream(this.nomeCaminhoImagem);
			fos.write(imagemArq);
			fos.flush();
			fos.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}
