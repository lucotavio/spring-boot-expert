package br.com.luciano.loja.exception;

public class PedidoNaoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public PedidoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
}
