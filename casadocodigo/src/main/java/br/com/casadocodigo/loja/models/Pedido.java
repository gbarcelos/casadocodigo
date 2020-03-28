package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.util.CollectionUtils;

public class Pedido {

	private String id;

	private String valor;

	private String dataPedido;

	private String titulos;

	public Pedido(PedidoResponse response) {

		this.id = response.getId().toString();

		this.valor = getValorFormatado(response.getValor());

		this.dataPedido = getDataPedidoFormatada(response.getData());

		this.titulos = getTitulos(response.getProdutos());
	}

	private String getValorFormatado(BigDecimal valor) {
		
		String valorFormatado = null;
		
		if (valor != null) {
		
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			valorFormatado = nf.format(valor);
		}
		return valorFormatado; 
	}

	private String getDataPedidoFormatada(Calendar data) {
		
		String dataPedidoFormatada = null;
		
		if (data != null) {
			
			Date dataPedido = data.getTime();
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			dataPedidoFormatada = dateFormat.format(dataPedido);
		}
		return dataPedidoFormatada;
	}

	private String getTitulos(List<Produto> produtos) {

		String titulos = null;

		if (!CollectionUtils.isEmpty(produtos)) {
			StringBuilder sb = new StringBuilder();
			for (Produto produto : produtos) {
				sb.append(produto.getDescricao());
				sb.append(", ");
			}
			titulos = sb.toString();
		}
		return titulos;
	}

	public String getId() {
		return id;
	}

	public String getValor() {
		return valor;
	}

	public String getDataPedido() {
		return dataPedido;
	}

	public String getTitulos() {
		return titulos;
	}

}
