package br.com.luciano.loja.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoDTO {

    private Integer id;

    @NotEmpty(message = "Campo descrição é obrigatório")
    private String descricao;

    @NotNull(message = "Campo preço é obrigatorio")
    private BigDecimal precoUnitario;
}
