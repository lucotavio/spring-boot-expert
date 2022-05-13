package br.com.luciano.loja;

import br.com.luciano.loja.repository.PedidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Autowired
	private PedidoRepository repository;

	@Autowired
	private ModelMapper mapper;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
