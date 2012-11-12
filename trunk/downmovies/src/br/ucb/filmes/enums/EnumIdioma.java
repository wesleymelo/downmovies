package br.ucb.filmes.enums;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum EnumIdioma {
	PT("portugues",1),
	EN("ingles",2),
	PT_EN("portuguesIngles",3),
	NAO_DISPONIVEL("naoDisponivel",4);
	
	private String nome;
	private int valor;
	private EnumIdioma(String nome, Integer valor) {
		setNome(nome);
		setValor(valor);
	}
	
	public static EnumIdioma findByValue(int valor) {
		for (EnumIdioma temp : values()) {
			if (temp.getValor() == valor)
				return temp;
		}
		return null;
	}
	
	public static List<EnumIdioma> getIdiomas(){
		List<EnumIdioma> idiomas = new ArrayList<EnumIdioma>();
		for (EnumIdioma temp : values()){
			idiomas.add(temp);
		}
		return idiomas;
	}
	
	
	public static Map<String, Integer> getMapaIdioma(){
		Map<String, Integer> idiomas = new LinkedHashMap<String, Integer>();
		for (EnumIdioma temp : values()){
			idiomas.put(temp.getNome(),temp.getValor());
		}
		return idiomas;
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
