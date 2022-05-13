package br.com.luciano.loja.service;

import java.math.BigDecimal;
import java.util.Arrays;

import br.com.luciano.loja.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.luciano.loja.entity.Cliente;
import br.com.luciano.loja.repository.ClienteRepository;
import br.com.luciano.loja.repository.ProdutoRepository;

@Service
public class DbService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	
	public void instanciaDB() {
		
		Cliente cliente1 = new Cliente("Luciano Otavio Gonçalves de Oliveira", "458.821.980-46" );
		Cliente cliente2= new Cliente("Karine Silvestre Ferreira", "196.431.220-50");
		Cliente cliente3 = new Cliente("Pedro Silvestre de Oliveira", "648.867.610-54");
		
		clienteRepository.saveAll(Arrays.asList(cliente1, cliente2, cliente3));

		Produto produto1 = new Produto(null, "Sabão em pó Omo", new BigDecimal(10.00));
		Produto produto2 = new Produto(null, "pizza sadia", new BigDecimal(9.60));
		Produto produto3 = new Produto(null, "Coca cola", new BigDecimal(8.90));

		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));
	
	}

}
