package br.ucb.filmes.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum EnumCategoria {
	ACAO("ACAO",1),
	AVENTURA("AVENTURA",2),
	CLASSICO("CLASSICO",3),
	COMEDIA("COMEDIA",4),
	DOCUMENTARIO("DOCUMENTARIO",5),
	DRAMA("	DRAMA",6),
	FAROESTE("FAROESTE",7),
	FICCAO("FICCAO",8),
	INFANTIL("INFANTIL",9),
	MUSICAL("MUSICAL",10),
	ROMANCE("ROMANCE",11),
	SUSPENSE("SUSPENSE",12),
	TERROR("TERROR",13),
	POLICIAL("POLICIAL",14),
	NAO_DISPONIVEL("Não disponível",15);
	
	
	private String nome;
	private Integer valor;
	
	private EnumCategoria(String nome, Integer valor) {
		setNome(nome);
		setValor(valor);
	}
	
	public static EnumCategoria findByCateg(int valor) {
		for (EnumCategoria temp : values()) {
			if (temp.getValor() == valor)
				return temp;
		}
		return null;
	}
	public static Map<String, Integer> getMapaCategoria(){
		Map<String, Integer> categoria = new LinkedHashMap<String, Integer>();
		for (EnumCategoria temp : values()){
			categoria.put(temp.getNome(),temp.getValor());
		}
		return categoria;
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
