package br.com.casadocodigo.loja.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.models.Usuario;

public class UsuarioValidation implements Validator {
	
	private static final int NUMERO_MINIMO_CARACTERES = 5;

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "nome", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "email", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "senha", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "confirmacaoSenha", "field.required");

		Usuario usuario = (Usuario) target;
		
		if (StringUtils.hasText(usuario.getSenha())) {
			
			if (usuario.getSenha().length() < NUMERO_MINIMO_CARACTERES) {
				errors.rejectValue("senha", "typeMismatch.usuario.senha.minlen");
			}

			if (!usuario.getSenha().equals(usuario.getConfirmacaoSenha())) {
				errors.rejectValue("confirmacaoSenha", "typeMismatch.usuario.senha");
			}
		}

		if (StringUtils.hasText(usuario.getEmail())) {
			if (!isEmailValid(usuario.getEmail())) {
				errors.rejectValue("email", "typeMismatch.usuario.email");
			}
		}
	}
	
	private boolean isEmailValid(String email) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
		return matcher.find();
	}
}
