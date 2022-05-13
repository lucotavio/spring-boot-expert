package br.com.luciano.loja.service;

import br.com.luciano.loja.exception.SenhaInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.luciano.loja.entity.Usuario;
import br.com.luciano.loja.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class UsuarioService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private PasswordEncoder encoder;

	public UserDetails autenticar(Usuario usuario){
		UserDetails user = loadUserByUsername(usuario.getLogin());
		boolean senhasBatem = encoder.matches(usuario.getSenha(), user.getPassword());

		if(senhasBatem){
			return user;
		}
		throw new SenhaInvalidaException("Senhas inválida");
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = repository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
		
		String[] roles;
		
		if(usuario.isAdmin()) {
			roles = new String[] {"ADMIN", "USER"};
		}else {
			roles = new String[] {"USER"};
		}
		
		return User.builder()
				   .username(usuario.getLogin())
				   .password(usuario.getSenha())
				   .roles(roles)
				   .build();
	}

	public Usuario save(Usuario usuario) {
		
		return repository.save(usuario);
	}

}
