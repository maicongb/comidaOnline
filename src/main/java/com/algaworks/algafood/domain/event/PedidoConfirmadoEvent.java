package com.algaworks.algafood.domain.event;

import com.algaworks.algafood.domain.model.Pedido;

import lombok.AllArgsConstructor;
import lombok.Getter;

//CLASSE IRÁ GUARDAR INFORMAÇÕES QUE PRECISAMOS

@Getter
@AllArgsConstructor
public class PedidoConfirmadoEvent {

	private Pedido pedido;
}
