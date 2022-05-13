package br.com.luciano.loja.controller;

import javax.validation.Valid;

import br.com.luciano.loja.dto.CredenciaisDTO;
import br.com.luciano.loja.dto.TokenDTO;
import br.com.luciano.loja.exception.SenhaInvalidaException;
import br.com.luciano.loja.security.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.luciano.loja.dto.UsuarioDTO;
import br.com.luciano.loja.entity.Usuario;
import br.com.luciano.loja.service.UsuarioService;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtService jwtService;
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> save(@RequestBody @Valid UsuarioDTO dto) {
		
		String senhaCriptografada = encoder.encode(dto.getSenha());
		Usuario usuario = mapper.map(dto, Usuario.class);
		usuario.setSenha(senhaCriptografada);
		usuario = service.save(usuario);
		
		dto = mapper.map(usuario, UsuarioDTO.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	@PostMapping("/auth")
	public ResponseEntity<TokenDTO> autenticar(@RequestBody CredenciaisDTO credenciaisDTO){
		try{
			Usuario usuario = Usuario.builder()
					.login(credenciaisDTO.getLogin())
					.senha(credenciaisDTO.getSenha())
					.build();

			UserDetails usuarioAutenticado = service.autenticar(usuario);
			String token = jwtService.gerarToken(usuario);
			TokenDTO tokenDTO = new TokenDTO(usuario.getLogin(), token);
			return ResponseEntity.ok().body(tokenDTO);
		}catch(UsernameNotFoundException | SenhaInvalidaException ex){
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());
		}
	}

}
