package com.nelioalves.domain.enums;

public enum Perfil {
	
	ADMIN(1, "ROLE_ADMIN"), 
	CLIENTE(2, "ROLE_CLIENTE");
	
	private int cod;
	private String descricao;
	
	private Perfil(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	//devolver um cliente atraves do codigo
	//método estático = pode rodar mesmo sem ter objeto instanciado
	public static Perfil toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		//percorre todos os valores possiveis(pessoa fisica e pessoa juridica) do tipo enumerado cliente
		for (Perfil x : Perfil.values()) {
			 if(cod.equals(x.getCod())) {
				 return x;
			 }
		}
		
		throw new IllegalArgumentException("Id inválido " + cod);
	}

}
