package br.ucb.filmes.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.ucb.filmes.beans.Categoria;
import br.ucb.filmes.dao.CategoriaDAO;

@FacesConverter(forClass=Categoria.class, value="CategoriaConverter")
public class CategoriaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value != null) {  
            return new CategoriaDAO().consult(Integer.parseInt(value));
        }  
        return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if(object != null && object instanceof Categoria)
			return ((Categoria)object).getId_categoria().toString();
		return null;
	}
	
}
