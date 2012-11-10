package br.ucb.filmes.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class FileUtil {
	private StreamedContent streamedfile;
	 public  void downloadFile(String caminho,String nomeArquivo,String nomeFilme) throws FileNotFoundException {    
		 InputStream stream = new FileInputStream(caminho+nomeArquivo+".torrent");
		 streamedfile = new DefaultStreamedContent(stream,"application/octet-stream",nomeFilme);
	 }
	public StreamedContent getStreamedfile() {
		return streamedfile;
	}
	public void setStreamedfile(StreamedContent streamedfile) {
		this.streamedfile = streamedfile;
	}
	 
}
