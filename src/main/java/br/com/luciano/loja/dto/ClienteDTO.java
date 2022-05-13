package br.com.luciano.loja.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ClienteDTO {

    private Integer id;

    @NotEmpty(message = "O campo nome é obrigatorio")
    private String nome;

    @CPF(message="Informe um numero de CPF válido")
    @NotEmpty(message = "O campo cpf é obrigatorio")
    private String cpf;
}
