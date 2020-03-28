package br.com.casadocodigo.loja.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.casadocodigo.loja.models.Pedido;
import br.com.casadocodigo.loja.models.PedidoResponse;

@Service
public class PedidoService {

	@Autowired
	private RestTemplate restTemplate;

	public List<Pedido> listar() {

		List<Pedido> pedidosList = null;

		PedidoResponse[] pedidos = obterPedidosDisponiveis();

		if (pedidos != null && pedidos.length > 0) {

			pedidosList = new ArrayList<Pedido>(pedidos.length);

			for (PedidoResponse pedidoesponse : pedidos) {
				pedidosList.add(new Pedido(pedidoesponse));
			}
		}
		return pedidosList;
	}

	private PedidoResponse[] obterPedidosDisponiveis() {

		ResponseEntity<PedidoResponse[]> response = restTemplate
				.getForEntity("https://book-payment.herokuapp.com/orders", PedidoResponse[].class);

		return response.getBody();
	}

}
