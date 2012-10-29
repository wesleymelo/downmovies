package br.ucb.filmes.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.ucb.filmes.beans.Categoria;
import br.ucb.filmes.dao.CategoriaDAO;

@FacesConverter(value="CategoriaConverter", forClass=Categoria.class)
public class CategoriaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value != null && !value.isEmpty()) {  
			System.out.println("Valor: "+Integer.parseInt(value));
            return new CategoriaDAO().consult(Integer.parseInt(value)); 
        }  
        return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if(object != null && object instanceof Categoria) {  
			System.out.println(((Categoria)object));
            return ((Categoria)object).getIdCategoria().toString();  
        }  
        return null;  
	}

}
