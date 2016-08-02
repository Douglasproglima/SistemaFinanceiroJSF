package com.douglasproglima.sistemafinanceiro.repositorio;

import java.util.List;

import com.douglasproglima.sistemafinanceiro.model.Lancamento;

public interface RepositorioLancamentos {

	public List<Lancamento> consultaTodas();
	
	//Salva o lancamento no repositório
	public Lancamento salvar(Lancamento lancamento);
	public void remover(Lancamento lancamento); 
}