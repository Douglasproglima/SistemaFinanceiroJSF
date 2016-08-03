package com.douglasproglima.sistemafinanceiro.servico;

@SuppressWarnings("serial")
public class RegraNegocioException extends Exception {
	
	public RegraNegocioException(String mensagem){
		super(mensagem);
	}
}