package br.com.casadocodigo.loja.exception;

public class EmailUsuarioJaCadastradoException extends RuntimeException {

	private static final long serialVersionUID = -6564794999697625565L;

	public EmailUsuarioJaCadastradoException(String message) {
		super(message);
	}
}
