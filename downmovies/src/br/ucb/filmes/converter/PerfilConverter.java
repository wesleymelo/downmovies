package br.ucb.filmes.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.ucb.filmes.beans.Perfil;
import br.ucb.filmes.dao.PerfilDAO;

@FacesConverter(value="PerfilConverter", forClass=Perfil.class)
public class PerfilConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value != null && !value.isEmpty()) {  
            return new PerfilDAO().consult(Integer.parseInt(value)); 
        }  
        return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if(object != null && object instanceof Perfil) {  
            return ((Perfil)object).getIdPerfil().toString();
        }  
        return null;  
	}

}
