package br.com.luciano.loja.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.luciano.loja.entity.Usuario;
import br.com.luciano.loja.validation.UniqueKey;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
	
	private Integer id; 

	@UniqueKey(columnName = "login", className = "Usuario", message = "{login.existe}")
	@NotEmpty(message = "{campo.login.obrigatorio}")
	private String login;
	
	@NotEmpty(message = "{campo.senha.obrigatorio}")
	private String senha;
	
	private boolean admin;

}
