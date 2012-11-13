package br.ucb.filmes.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class FileUtil {
	
	
	
	private StreamedContent streamedfile;
	private String defineCaminhoArq(String nomeArqOriginal,
			String caminhoArqOriginal) {
		String separador = null;
		separador = "\\";
		return caminhoArqOriginal + separador + nomeArqOriginal;
	}

	
	public void gerarArquivoDownloadPDF(Map parametros, String formato,
			String nomeArqOriginal, String caminhoArqOriginal,
			String nomeArqGerado, Collection colecao) throws JRException,
			Exception {

		byte[] relatorioByte = gerarRelatorioPdfParaWeb(parametros,
				defineCaminhoArq(nomeArqOriginal, caminhoArqOriginal), colecao);

		InputStream stream = new ByteArrayInputStream(relatorioByte);
		streamedfile = new DefaultStreamedContent(stream, formato,
				nomeArqGerado);
	}
	@SuppressWarnings(value = { "all" })
	private byte[] gerarRelatorioPdfParaWeb(Map parametros,
			String caminhoArqOrigem, Collection colecao) throws Exception,
			JRException {

		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;
		byte[] pdf = null;

		try {
			JRDataSource dataSource = null;
			if (colecao != null && !colecao.isEmpty()) {
				dataSource = new JRBeanCollectionDataSource(colecao);
			}
			File file = new File(caminhoArqOrigem);

			jasperReport = (JasperReport) JRLoader.loadObject(file);
			if (dataSource != null) {
				jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);
			} else {
				jasperPrint = JasperFillManager.fillReport(jasperReport,parametros);
			}

			pdf = JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (JRException jre) {
			jre.printStackTrace();
		}
		return pdf;
	}

	
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
