package com.algaworks.algafood.domain.exception;

//A entidade EntidadeNaoEncontraException já tem o ResponseStatus
//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public EstadoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	//this chama o contrutor de cima
	public EstadoNaoEncontradoException(Long estadoId) {
		this(String.format("Não existe um cadastro de estado com este codigo %d", estadoId));
	}

}
