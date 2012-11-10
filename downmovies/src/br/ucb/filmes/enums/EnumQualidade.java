package br.ucb.filmes.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum EnumQualidade {
	CAM("CAM",1),
	TS("TS",2),
	TC("MPEG",3),
	SCR("SCR",4),
	DVDscr("DVDscr",5),
	DVDRip("DVDRip",6),
	R5("R5",7),
	NAO_DISPONIVEL("Não disponível",8);
	
	String nome;
	Integer valor;
	private EnumQualidade(String nome, Integer valor) {
		setNome(nome);
		setValor(valor);
	}
	
	public static EnumQualidade findByQuality(int valor) {
		for (EnumQualidade temp : values()) {
			if (temp.getValor() == valor)
				return temp;
		}
		return null;
	}
	public static Map<String, Integer> getMapaQualidade(){
		Map<String, Integer> qualidades = new LinkedHashMap<String, Integer>();
		for (EnumQualidade temp : values()){
			qualidades.put(temp.getNome(),temp.getValor());
		}
		return qualidades;
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

