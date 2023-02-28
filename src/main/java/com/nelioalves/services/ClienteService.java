package com.nelioalves.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.domain.Cliente;
import com.nelioalves.dto.ClienteDto;
import com.nelioalves.repositories.ClienteRepository;
import com.nelioalves.services.exceptions.DataIntegrityException;

@Service
public class ClienteService {
	
	//para fazer a injeção de dependencia
	@Autowired
	private ClienteRepository repo;
	
	//recebe um id e retorna o cliente correspondente. se nao encontrar lanca excecao
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new com.nelioalves.services.exceptions.ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		} 
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
		}
	}
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(int page, int linesPerPage, Direction direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, direction, orderBy);
		return repo.findAll(pageRequest);
	} 
	
	public Cliente fromDto(ClienteDto objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	//metodo auxiliar dentro da classe, por isso é private
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
}
