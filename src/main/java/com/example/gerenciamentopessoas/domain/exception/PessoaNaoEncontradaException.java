package com.example.gerenciamentopessoas.domain.exception;

public class PessoaNaoEncontradaException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public PessoaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	

}
