package com.douglasproglima.sistemafinanceiro.repositorio;

import java.util.List;

import com.douglasproglima.sistemafinanceiro.model.Lancamento;

public interface FichaDeLancamentos {
	
	public List<Lancamento> consultaTodos();
	public Lancamento guardar(Lancamento lancamento);
	public void remover(Lancamento lancamento);
}