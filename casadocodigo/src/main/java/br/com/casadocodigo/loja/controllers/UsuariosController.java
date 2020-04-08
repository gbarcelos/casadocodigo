package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.exception.EmailUsuarioJaCadastradoException;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.service.UsuarioService;
import br.com.casadocodigo.loja.validation.UsuarioValidation;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private UsuarioService service;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new UsuarioValidation());
	}

	@RequestMapping(method = RequestMethod.GET, name = "usuarios_lista")
	public ModelAndView listar() {
		List<Usuario> usuarios = service.listar();
		ModelAndView modelAndView = new ModelAndView("usuarios/lista");
		modelAndView.addObject("usuarios", usuarios);
		return modelAndView;
	}

	@RequestMapping("/form")
	public ModelAndView form(Usuario usuario) {
		return new ModelAndView("usuarios/form");
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return form(usuario);
		}

		try {
			service.salvar(usuario);

		} catch (EmailUsuarioJaCadastradoException e) {
			result.rejectValue("email", e.getMessage(), e.getMessage());
			return form(usuario);
		}

		redirectAttributes.addFlashAttribute("message", "Usuário cadastrado com sucesso!");
		return new ModelAndView("redirect:/usuarios");
	}
}
