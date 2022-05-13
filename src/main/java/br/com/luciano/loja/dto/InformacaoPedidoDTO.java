package br.com.luciano.loja.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InformacaoPedidoDTO {

    private Integer id;
    private BigDecimal total;
    private String clienteCpf;
    private String clienteNome;
    private String status;
    private List<InformacaoItemPedidoDTO> itens;
}
