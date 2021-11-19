package com.algaworks.algafood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.algaworks.algafood.domain.event.PedidoConfirmadoEvent;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.service.EnvioEmailService;
import com.algaworks.algafood.domain.service.EnvioEmailService.Mensagem;

//CLASSE FICARÁ ESCUTANDO OS EVENTOS LANÇADOS

//@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
//COM ESTA ANOTAÇÃO O SPRING OBRIGATORIAMENTE FARA O UPDATE E ENVIARA O EMAIL
//SE ALGUMA DESTAS AÇÕES DEREM ERRO, SERÁ FEITO O ROLLBACK NO BANCO

//@TransactionalEventListener NESTE CASO, O SPRING FARA O UPDATE E O ENVIO DE 
//EMAIL PODERÁ DAR ERRO O NÃO, NESTE CASO NÃO IRA OCORRER O ROLLBACK

@Component
public class NotificacaoClientePedidoConfirmadoListener {
	
	@Autowired
	private EnvioEmailService envioEmail;

	@TransactionalEventListener
	public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
		
		//recupera o pedido dentro do evento
		Pedido pedido = event.getPedido();
		
		var mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
				.corpo("pedido-confirmado.html")
				.variavel("pedido", pedido)
				.destinatario(pedido.getCliente().getEmail())
				.build();
		
		envioEmail.enviar(mensagem);
	}
	
}
