package com.algaworks.algafood.domain.model;

import java.util.Arrays;
import java.util.List;

//Um parâmetro vararg pode receber 0, muitos ou um array de parâmetros
// StatusPedido... statusAnteriores é um vararg
public enum StatusPedido {

	CRIADO("Criado"),
	CONFIRMADO("Confirmado", CRIADO),
	ENTREGUE("Entregue", CONFIRMADO),
	CANCELADO("Cancelado", CRIADO);
	
	private String descricao;
	private List<StatusPedido> statusAnteriores;
	
	StatusPedido(String descricao, StatusPedido... statusAnteriores) {
		this.descricao = descricao;
		this.statusAnteriores = Arrays.asList(statusAnteriores);
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	//this é o status atual
	public boolean naoPodeAlterarPara(StatusPedido novoStatus) {
		return !novoStatus.statusAnteriores.contains(this);
	}
	
	public boolean podeAlterarPara(StatusPedido novoStatus) {
		return !naoPodeAlterarPara(novoStatus);
	}
	
}
