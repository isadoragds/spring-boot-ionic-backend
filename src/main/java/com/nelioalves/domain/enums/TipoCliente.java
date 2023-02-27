package com.nelioalves.domain.enums;

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String descricao;
	
	private TipoCliente(int cod, String descricao) {
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
	public static TipoCliente toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		//percorre todos os valores possiveis(pessoa fisica e pessoa juridica) do tipo enumerado cliente
		for (TipoCliente x : TipoCliente.values()) {
			 if(cod.equals(x.getCod())) {
				 return x;
			 }
		}
		
		throw new IllegalArgumentException("Id inválido " + cod);
	}
}
