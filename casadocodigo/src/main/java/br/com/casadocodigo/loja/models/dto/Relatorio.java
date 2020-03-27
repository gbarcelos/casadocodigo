package br.com.casadocodigo.loja.models.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.util.CollectionUtils;

import br.com.casadocodigo.loja.models.Produto;

public class Relatorio {

	private Calendar dataGeracao;

	private int quantidade;

	private List<Produto> produtos;

	public Relatorio(List<Produto> produtos) {
		this.dataGeracao = Calendar.getInstance();

		if (CollectionUtils.isEmpty(produtos)) {
			this.quantidade = 0;
			this.produtos = new ArrayList<Produto>();

		} else {
			this.quantidade = produtos.size();
			this.produtos = produtos;

		}
	}

	public Calendar getDataGeracao() {
		return dataGeracao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}
}
