package br.ucb.filmes.util;


import java.util.Collection;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.PartialViewContext;



public class FacesUtil {
	
    public static void mensInfo(String message) {
        mensagem(message, FacesMessage.SEVERITY_INFO);
    }

    public static void mensErro(String message) {
    	mensagem(tratarExcecao(message), FacesMessage.SEVERITY_ERROR);
    }

    public static String tratarExcecao(String message){
    	if (message.contains("java.") || message.contains("br.com")) {
    		String[] mensagensSplit = message.split(":");
			return mensagensSplit[(mensagensSplit.length - 1)].trim();
    	}
    	return message;
    }
    
    /**
     * M�todo que realiza tratamento de mensagens.
     * O objetivo do m�todo e atender a necessidade de passar v�rias mensagens ou 
     * de atender a necessidade de uma quebra de linha (a tag br do html) nas mensagem.
     * Ao passar v�rias mensagens � necess�rio come�ar a mensagem com #(charp) e separar a mensagem com outro #(charp)
     * Ex.: #Isto � uma mensagem.#Isto � outra mensagem.#Fim da mensagem.
     * @param message 
     * @param severity (FacesMessage.SEVERITY_ERROR, FacesMessage.SEVERITY_FATAL, FacesMessage.SEVERITY_INFO, FacesMessage.SEVERITY_WARN)
     */
    public static void mensFull(String message, FacesMessage.Severity severity) {
    	if(message != null && message.startsWith("#")) {
    		String[] msgDiv = message.split("#");
    		for (String msg : msgDiv) {
    			if(!msg.isEmpty()) {
    				mensagem(tratarExcecao(msg), severity);
    			}
			}
    	} else if(message != null) {
    		mensagem(tratarExcecao(message), severity);
    	}
    }

    public static void mensagem(String message, 
    		FacesMessage.Severity severity) {
    	System.out.println(message);
        FacesContext.getCurrentInstance().
        	addMessage(null, new FacesMessage(severity, message, null));
    }
    
    public static String get(String param) {
    	return (String) FacesContext.getCurrentInstance().
			getExternalContext().
			getRequestParameterMap().get(param);
    }   

    /**
     * Atenc��o! Utilizar o componente FormUtil
     */
    @Deprecated
    public static void limpaValoresDoFormulario() {
		System.out.println("Limpando valores do form");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		PartialViewContext partialViewContext = facesContext.getPartialViewContext();

		Collection<String> renderIds = partialViewContext.getRenderIds();
		for (String renderId : renderIds) {
		    UIComponent component = FacesContext.getCurrentInstance().getViewRoot().findComponent(renderId);
		    cleanSubmittedValues(component);
		}
		
	}
	
	/**
	* Limpa os dados dos componentes de edi��o e de seus filhos,
	* recursivamente. Checa se o componente � inst�ncia de EditableValueHolder
	* e 'reseta' suas propriedades.
	* 
	* Quando este m�todo, por algum motivo, n�o funcionar, parta para ignor�ncia
	* e limpe o componente assim:
	* 
	* component.getChildren().clear()
	* 
	* 
	*/
	@SuppressWarnings("rawtypes")
	private static void cleanSubmittedValues(UIComponent component) {
		if (component instanceof EditableValueHolder) {
            EditableValueHolder evh = (EditableValueHolder) component;
            evh.setSubmittedValue(null);
            evh.setValue(null);
            evh.setLocalValueSet(false);
            evh.setValid(true);
        }
        // Dependendo de como se implementa um Composite Component, ele retorna ZERO
        // na busca por filhos. Nesse caso devemos iterar sobre os componentes que o
        // comp�e de forma diferente.
        if(UIComponent.isCompositeComponent(component)) {
            Iterator i = component.getFacetsAndChildren();
            while(i.hasNext()) {
                UIComponent comp = (UIComponent) i.next();
                limparSubComponentes(comp);
            }
        }
        limparSubComponentes(component);
    }

	private static void limparSubComponentes(UIComponent comp) {
		if (comp.getChildCount() > 0) {
		    for (UIComponent child : comp.getChildren()) {
		        cleanSubmittedValues(child);
		    }
		}
	}

}