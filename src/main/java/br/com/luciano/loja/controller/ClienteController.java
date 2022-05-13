package br.com.luciano.loja.controller;

import java.util.List;
import java.util.stream.Collectors;
import br.com.luciano.loja.dto.ClienteDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import br.com.luciano.loja.entity.Cliente;
import br.com.luciano.loja.exception.RegraNegocioException;
import br.com.luciano.loja.service.ClienteService;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	private ClienteService service;

	private ModelMapper mapper;

	public ClienteController(ClienteService service, ModelMapper mapper) {
		super();
		this.service = service;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll(){
		List<Cliente> clientes = service.findAll();

		List<ClienteDTO> clienteDTOs = clientes.stream()
				.map(cliente -> mapper.map(cliente, ClienteDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(clienteDTOs);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {

			Cliente cliente = service.findById(id);
			ClienteDTO clienteDTO = mapper.map(cliente, ClienteDTO.class);
			return ResponseEntity.ok().body(clienteDTO);
	}

	@PostMapping
	public ResponseEntity<ClienteDTO> save(@RequestBody @Valid ClienteDTO clienteDTO) {

		Cliente cliente = mapper.map(clienteDTO, Cliente.class);
		cliente = service.save(cliente);
		clienteDTO = mapper.map(cliente, ClienteDTO.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		
		try {
			service.findById(id);
			service.deleteById(id);
			return ResponseEntity.noContent().build();	
			
		}catch(RegraNegocioException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
		
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> update(@PathVariable Integer id, @RequestBody Cliente cliente){
		cliente.setId(id);
		cliente = service.save(cliente);
		return ResponseEntity.ok().body(cliente);
	}
	
	@GetMapping("/filtro")
	public ResponseEntity<List<Cliente>> findByFilter(Cliente filtro){
		List<Cliente> clientes = service.findByFilter(filtro);
		return ResponseEntity.ok().body(clientes);
	}
}
