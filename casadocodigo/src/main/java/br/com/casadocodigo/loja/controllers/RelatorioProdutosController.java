package br.com.casadocodigo.loja.controllers;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.dto.Relatorio;

@RestController
public class RelatorioProdutosController {

	@Autowired
	private ProdutoDAO produtoDao;

	@ResponseBody
	@RequestMapping(value = "/relatorio-produtos", method = RequestMethod.GET)
	public Relatorio todosOsLivrosLançados(
			@RequestParam(name = "data", required = false) Calendar data) {
		return new Relatorio(produtoDao.filtrar(data));
	}

}
