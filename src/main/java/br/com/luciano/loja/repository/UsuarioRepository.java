package br.com.luciano.loja.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.luciano.loja.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Optional<Usuario> findByLogin(String login);
}
