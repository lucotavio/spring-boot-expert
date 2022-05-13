package br.com.luciano.loja.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import br.com.luciano.loja.entity.Cliente;
import br.com.luciano.loja.repository.ClienteRepository;

@Service
public class ClienteService {
	
	private ClienteRepository repository;

	public ClienteService(ClienteRepository repository) {
		this.repository = repository;
	}
	
	public List<Cliente> findAll(){
		return repository.findAll();
	}

	public Cliente findById(Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new DataIntegrityViolationException("Código de cliente inválido"));
	}

	public Cliente save(Cliente cliente) {
		return repository.save(cliente);
	}

	public void deleteById(Integer id) {
		repository.deleteById(id);
		
	}
	
	public List<Cliente> findByFilter(Cliente filter){
		
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		
		Example<Cliente> example = Example.of(filter, matcher);
		List<Cliente> clientes = repository.findAll(example);
		return clientes;
	}

}
