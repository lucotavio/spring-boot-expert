package br.com.luciano.loja.service;

import br.com.luciano.loja.entity.Produto;
import br.com.luciano.loja.repository.ProdutoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutoService {

    private ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> findAll(){
        return repository.findAll();
    }

    public Produto findById(Integer id) {

        try{
            return repository.findById(id)
                    .orElseThrow(() -> new DataIntegrityViolationException("Produto não encontrado"));
        }catch(DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("Produto não encontrado");
        }

    }

    public Produto save(Produto produto) {
        return repository.save(produto);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);

    }

    public List<Produto> findByFilter(Produto filter){

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Produto> example = Example.of(filter, matcher);

        List<Produto> produtos = repository.findAll(example);
        return produtos;
    }
}
