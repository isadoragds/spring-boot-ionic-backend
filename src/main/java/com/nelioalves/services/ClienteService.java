package com.nelioalves.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.domain.Cliente;
import com.nelioalves.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	//para fazer a injeção de dependencia
	@Autowired
	private ClienteRepository repo;
	
	//recebe um id e retorna o cliente correspondente. se nao encontrar lanca excecao
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new com.nelioalves.services.exceptions.ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
}
