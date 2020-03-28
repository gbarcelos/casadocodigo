package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.models.Pedido;
import br.com.casadocodigo.loja.service.PedidoService;

@Controller
@RequestMapping("/pedidos")
public class PedidosController {

	@Autowired
	private PedidoService service;

	@RequestMapping(method = RequestMethod.GET, name = "pedidos_lista")
	public ModelAndView listar() {
		List<Pedido> pedidos = service.listar();
		ModelAndView modelAndView = new ModelAndView("pedidos/lista");
		modelAndView.addObject("pedidos", pedidos);
		return modelAndView;
	}

}
