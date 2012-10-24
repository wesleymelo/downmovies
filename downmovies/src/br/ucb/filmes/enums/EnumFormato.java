package br.ucb.filmes.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum EnumFormato {
	AVI("AVI",1),
	RMVB("RMVB",2),
	MPEG("MPEG",3),
	DivX("DivX",4),
	XviD("XviD",5),
	VCD("VCD",6),
	NAO_DISPONIVEL("Não disponível",7);
	
	private String nome;
	private Integer valor;
	private EnumFormato(String nome, Integer valor) {
		setNome(nome);
		setValor(valor);
	}
	
	public static EnumFormato findByFormat(int valor) {
		for (EnumFormato temp : values()) {
			if (temp.getValor() == valor)
				return temp;
		}
		return null;
	}
	
	public static Map<String, Integer> getMapaFomato(){
		Map<String, Integer> formatos = new LinkedHashMap<String, Integer>();
		for (EnumFormato temp : values()){
			formatos.put(temp.getNome(),temp.getValor());
		}
		return formatos;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getValor() {
		return valor;
	}
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
	 
}
