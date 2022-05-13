package br.com.luciano.loja.controller;

import br.com.luciano.loja.dto.ProdutoDTO;
import br.com.luciano.loja.entity.Produto;
import br.com.luciano.loja.exception.RegraNegocioException;
import br.com.luciano.loja.service.ProdutoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.List;
import static java.util.stream.Collectors.*;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	private ProdutoService service;

	private ModelMapper mapper;

	public ProdutoController(ProdutoService service, ModelMapper mapper) {
		super();
		this.service = service;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<List<ProdutoDTO>> findAll(){
		List<Produto> produtos = service.findAll();

		List<ProdutoDTO> produtoDTOs = produtos.stream()
				.map(produto -> mapper.map(produto, ProdutoDTO.class))
				.collect(toList());

		return ResponseEntity.ok().body(produtoDTOs);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Integer id) {
		try {
			Produto produto = service.findById(id);
			return ResponseEntity.ok().body(produto);	
			
		}catch(RegraNegocioException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<ProdutoDTO> save(@RequestBody @Valid ProdutoDTO produtoDTO) {

		Produto produto = mapper.map(produtoDTO, Produto.class);
		produto = service.save(produto);
		produtoDTO = mapper.map(produto, ProdutoDTO.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoDTO);
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
	public ResponseEntity<Produto> update(@PathVariable Integer id, @RequestBody Produto produto){
		produto.setId(id);
		produto = service.save(produto);
		return ResponseEntity.ok().body(produto);
	}
	
	@GetMapping("/filtro")
	public ResponseEntity<List<Produto>> findByFilter(@RequestBody  Produto filtro){
		List<Produto> produtos = service.findByFilter(filtro);
		return ResponseEntity.ok().body(produtos);
	}
}
