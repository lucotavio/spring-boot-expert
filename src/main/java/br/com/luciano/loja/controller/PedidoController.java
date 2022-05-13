package br.com.luciano.loja.controller;

import br.com.luciano.loja.converter.ConverterStatusPedidoParaString;
import br.com.luciano.loja.converter.ConverterStringParaStatusPedido;
import br.com.luciano.loja.dto.AtualizacoStatusPedidoDTO;
import br.com.luciano.loja.dto.InformacaoPedidoDTO;
import br.com.luciano.loja.dto.PedidoDTO;
import br.com.luciano.loja.entity.Pedido;
import br.com.luciano.loja.enumeration.StatusPedido;
import br.com.luciano.loja.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import static java.util.stream.Collectors.toList;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> findAll(){
        ConverterStatusPedidoParaString converter = new ConverterStatusPedidoParaString();
        mapper.addConverter(converter);
        List<Pedido> pedidos = service.findAll();

        List<PedidoDTO> pedidoDTOList = pedidos.stream()
                .map(pedido -> mapper.map(pedido, PedidoDTO.class))
                .collect(toList());

        return ResponseEntity.ok().body(pedidoDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InformacaoPedidoDTO> getInformacoesPedidoDTO(@PathVariable Integer id){
        ConverterStatusPedidoParaString converter = new ConverterStatusPedidoParaString();
        mapper.addConverter(converter);
        Pedido pedido = service.getCompleteOrder(id);
        InformacaoPedidoDTO informacaoPedidoDTO = mapper .map(pedido, InformacaoPedidoDTO.class);
        return ResponseEntity.ok().body(informacaoPedidoDTO);
    }

    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody @Valid PedidoDTO pedidoDTO){
        ConverterStringParaStatusPedido converter = new ConverterStringParaStatusPedido();
        mapper.addConverter(converter);
        Pedido pedido = mapper.map(pedidoDTO, Pedido.class);
        pedido = service.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido.getId());
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody AtualizacoStatusPedidoDTO dto){
        StatusPedido status = StatusPedido.valueOf(dto.getStatus());
        service.updateStatus(id, status);
    }

}
