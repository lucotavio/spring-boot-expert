package br.com.luciano.loja.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InformacaoItemPedidoDTO {

    private String produtoDescricao;
    private BigDecimal produtoPrecoUnitario;
    private Integer quantidade;
}
