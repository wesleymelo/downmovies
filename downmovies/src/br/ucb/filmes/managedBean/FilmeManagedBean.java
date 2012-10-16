package br.ucb.filmes.managedBean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.ucb.filmes.beans.Categoria;
import br.ucb.filmes.beans.Filme;
import br.ucb.filmes.dao.CategoriaDAO;
import br.ucb.filmes.dao.FilmeDAO;

@ManagedBean
public class FilmeManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Filme filme;
	private List<Filme> filmes;
	private List<Categoria> categorias;
	private UploadedFile file;

	public FilmeManagedBean() {
		this.filme = new Filme();
		this.categorias = new CategoriaDAO().recoveryAll();
	} 
	
	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
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

	public String insert(){
		FacesMessage msg = new FacesMessage();
		
		FilmeDAO dao = new FilmeDAO();
		
		dao.insert(getFilme());
		
		msg.setSummary("Filme inserido com sucesso");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		setFilmes(dao.recoveryAll());
		
		return "filmeUploadForm";
		
	}
	
	public String cadastrar(){
		return "filmeForm";
	}
	
	
	public String fileUploadAction(FileUploadEvent event) {
		
		System.out.println("hhhhhhhcassete!");
		
        try {
           
           
            
            String caminho = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestContextPath();
            
            
            
 
 // Aqui cria o diretorio caso não exista
            File file = new File(caminho);
            file.mkdirs();
            
            System.out.println("hhhhhhhcassete!");
    		
    		System.out.println(event.getFile().getFileName());
    		
    		
    		System.out.println("Caminho"+caminho);
            
            
            byte[] arquivo = event.getFile().getContents();
            
            
            System.out.println("arq"+arquivo);
            caminho = caminho +"/"+event.getFile().getFileName();    
      
 // esse trecho grava o arquivo no diretório
            FileOutputStream fos = new FileOutputStream(caminho);
            fos.write(arquivo);
            fos.close();
            
            FacesMessage msg = new FacesMessage("Sucesso", event.getFile().getFileName() + " foi carregado.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
            
        } catch (Exception ex) {
            System.out.println("Erro no upload de imagem" + ex);
        }
        
        return "filmeList";
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
