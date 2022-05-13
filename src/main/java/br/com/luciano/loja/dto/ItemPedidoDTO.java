package br.com.luciano.loja.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoDTO {

    private Integer id;
    private Integer produtoId;
    private Integer quantidade;
}
