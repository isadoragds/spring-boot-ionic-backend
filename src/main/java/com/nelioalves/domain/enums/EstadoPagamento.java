package com.nelioalves.domain.enums;

public enum EstadoPagamento {
	
	PENDENTE(1, "Pendente"), 
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String descricao;
	
	private EstadoPagamento(int cod, String descricao) {
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
	public static EstadoPagamento toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		//percorre todos os valores possiveis(pessoa fisica e pessoa juridica) do tipo enumerado cliente
		for (EstadoPagamento x : EstadoPagamento.values()) {
			 if(cod.equals(x.getCod())) {
				 return x;
			 }
		}
		
		throw new IllegalArgumentException("Id inválido " + cod);
	}

}
