package br.com.luciano.loja.service;

import br.com.luciano.loja.entity.Cliente;
import br.com.luciano.loja.entity.ItemPedido;
import br.com.luciano.loja.entity.Pedido;
import br.com.luciano.loja.entity.Produto;
import br.com.luciano.loja.enumeration.StatusPedido;
import br.com.luciano.loja.exception.RegraNegocioException;
import br.com.luciano.loja.repository.ClienteRepository;
import br.com.luciano.loja.repository.PedidoRepository;
import br.com.luciano.loja.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public Pedido findById(Integer id){
        return pedidoRepository.findById(id).orElseThrow(() -> new RegraNegocioException("Código do pedido inválido"));
    }

    public Pedido save(Pedido pedido){

        this.existCliente(pedido.getCliente().getId());
        for (ItemPedido itemPedido: pedido.getItens()) {
            this.existProduto(itemPedido.getProduto().getId());
        }
        pedido.setStatus(StatusPedido.REALIZADO);
        return pedidoRepository.save(pedido);
    }

    public Pedido getCompleteOrder(Integer id){
        Pedido pedido = pedidoRepository.findByIdFetchItens(id);
        if(pedido == null){
            throw new RegraNegocioException("Código de pedido inválido");
        }
        return pedido;
    }

    public void updateStatus(Integer id, StatusPedido status){

        Pedido pedido = findById(id);
        pedido.setStatus(status);
        pedidoRepository.save(pedido);

    }

    private void existCliente(Integer idCliente){
        Optional<Cliente> resultado = clienteRepository.findById(idCliente);
        if(resultado.isEmpty()){
            throw new RegraNegocioException("Código de cliente inválido");
        }
    }

    private void existProduto(Integer idProduto){
        Optional<Produto> resultado = produtoRepository.findById(idProduto);
        if(resultado.isEmpty()){
            throw new RegraNegocioException("Código de produto inválido");
        }
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }
}
