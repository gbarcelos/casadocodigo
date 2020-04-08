package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Usuario;

@Repository
@Transactional
public class UsuarioDAO implements UserDetailsService {

	@PersistenceContext
	private EntityManager manager;

	public Usuario loadUserByUsername(String email) {

		List<Usuario> usuarios = getUsuarioByEmail(email);

		if (usuarios.isEmpty()) {
			throw new UsernameNotFoundException("Usuario " + email + " não foi encontrado");
		}
		return usuarios.get(0);
	}

	public Usuario findByEmail(String email) {

		List<Usuario> usuarios = getUsuarioByEmail(email);

		if (!usuarios.isEmpty()) {
			return usuarios.get(0);
		}
		return null;
	}

	public void salvar(Usuario usuario) {
		manager.persist(usuario);
	}

	private List<Usuario> getUsuarioByEmail(String email) {
		return manager.createQuery("select u from Usuario u where email = :email", Usuario.class)
				.setParameter("email", email).getResultList();
	}

	public List<Usuario> listar() {
		return manager.createQuery("select u from Usuario u ", Usuario.class).getResultList();
	}
}