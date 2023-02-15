package com.example.gerenciamentopessoas.domain.exception;

public class EnderecoNaoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public EnderecoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

}
