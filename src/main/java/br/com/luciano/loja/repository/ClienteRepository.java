package br.com.luciano.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.luciano.loja.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
