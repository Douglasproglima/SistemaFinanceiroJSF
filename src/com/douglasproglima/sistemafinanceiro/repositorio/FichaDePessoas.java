package com.douglasproglima.sistemafinanceiro.repositorio;

import java.util.List;

import com.douglasproglima.sistemafinanceiro.model.Pessoa;

public interface FichaDePessoas {
	
	public List<Pessoa> consultaTodas();
	public Pessoa consultaPorCodigo(Integer codigo);
	public Pessoa comDadosIguais(Pessoa pessoa);
	public Pessoa guardar(Pessoa pessoa);
	public void remover(Pessoa pessoa);
}