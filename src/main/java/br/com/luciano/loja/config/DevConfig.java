package br.com.luciano.loja.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.com.luciano.loja.service.DbService;


@Configuration
public class DevConfig {
	
	@Autowired
	private DbService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String value;
	
	@Bean
	public void instanciaDB() {
		
		if(value.equals("update")) {
			this.dbService.instanciaDB();
		}	
	}

}
