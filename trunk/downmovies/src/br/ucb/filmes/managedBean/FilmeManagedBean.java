package br.ucb.filmes.managedBean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import br.ucb.filmes.beans.Filme;
import br.ucb.filmes.dao.FilmeDAO;
import br.ucb.filmes.enums.EnumFormato;
import br.ucb.filmes.enums.EnumQualidade;
import br.ucb.filmes.util.FacesUtil;

@ManagedBean
public class FilmeManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Filme filme;
	private List<Filme> filmes;
	private UploadedFile file;
	
	public FilmeManagedBean() {
		filme = new Filme();
	}
	

	public void upload(FileUploadEvent event){
		file = event.getFile();
		
	}
	
	public Map<String, Integer> getMapFormato() {
		return EnumFormato.getMapaFomato();
	}


	public  Map<String, Integer> getMapQualidade() {
		return EnumQualidade.getMapaQualidade();
	}
	
	
	public void cadastraFilme()	{
		try {
			System.out.println("Qualidade:"+filme.getQualidade());
			System.out.println("Formato:"+filme.getFormato());
			
			
			FilmeDAO insert = new FilmeDAO();
			
			insert.insert(filme);
			FacesUtil.mensInfo("Filme cadastrado com Sucesso");
			
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.mensErro("Erro ao cadastrar filme");
		}
		
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
	
	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
		
	public String cadastrar(){
		return "filmeForm";
	}
	public void fileUploadActionImagem(FileUploadEvent event) {
		
	}
	
	public void fileUploadActionTorrent(FileUploadEvent event) {
		
        try {
            
            String caminho = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("\\filmes\\");
            
            
            
 
 // Aqui cria o diretorio caso não exista
            File file = new File(caminho);
            file.mkdirs();
            
           
    		
    		System.out.println(event.getFile().getFileName());
    		
    		
    		System.out.println("Caminho: "+caminho);
            
            
            byte[] arquivo = event.getFile().getContents();
            
            
            System.out.println("arq"+arquivo);
            caminho = caminho +"\\"+event.getFile().getFileName();    
      
            
            System.out.println("Caminho: "+caminho);
 // esse trecho grava o arquivo no diretório
            FileOutputStream fos = new FileOutputStream(caminho);
            fos.write(arquivo);
            fos.close();
            
            FacesUtil.mensInfo("O arquivo "+event.getFile().getFileName() + " foi carregado com Sucesso");
            
        } catch (Exception ex) {
            System.out.println("Erro no upload de imagem" + ex);
        }
        

    }
	
	
	
	
	
	public String uploadFilme(){
		
		
		System.out.println("hhhhhhhcassete!");
		
		System.out.println(file);
		
		if(getFile() != null){
			FacesMessage msg = new FacesMessage("Sucesso", getFile().getFileName() + " foi carregado.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
			
			String caminho = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("" + "\\filmes\\" + getFile().getFileName());
			
			
			
			System.out.println(getFile().getFileName());
			
			System.out.println(caminho);
			
			byte[] conteudo = getFile().getContents();
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(caminho);
				fos.write(conteudo);
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return "filmeList";
			
		}
		
		System.out.println("Ah nem!");
		
		return "filmeList";
		

	}
	
}
