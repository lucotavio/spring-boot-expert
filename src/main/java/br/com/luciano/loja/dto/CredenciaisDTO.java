package br.com.luciano.loja.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CredenciaisDTO {

    private String login;
    private String senha;
}
