package br.com.luciano.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.luciano.loja.entity.Produto;

public interface ProdutoRepository  extends JpaRepository<Produto, Integer>{

}
