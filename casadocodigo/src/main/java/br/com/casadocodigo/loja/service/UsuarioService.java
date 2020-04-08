package br.com.casadocodigo.loja.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.exception.EmailUsuarioJaCadastradoException;
import br.com.casadocodigo.loja.models.Usuario;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioDAO dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public void salvar(Usuario usuario) {
		
		Usuario usuarioBanco = dao.findByEmail(usuario.getEmail());

		if (usuarioBanco != null) {
			throw new EmailUsuarioJaCadastradoException("E-mail já cadastrado");
		}
		
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		
		dao.salvar(usuario);
	}

	public List<Usuario> listar() {
		return dao.listar();
	}
}
