package br.com.luciano.loja.dto;

import br.com.luciano.loja.validation.NotEmptyList;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PedidoDTO {

    private Integer id;

    @NotNull(message = "{campo.codigo-cliente.obrigatorio}")
    private Integer clienteId;

    @NotNull(message = "{campo.total-pedido.obrigatorio}")
    private BigDecimal total;

    @NotNull(message = "{campo.status-pedido.obrigatorio=}")
    private String status;

    @NotEmptyList(message = "{campo.items-pedido.obrigatorio}")
    private List<ItemPedidoDTO> itens;
}
