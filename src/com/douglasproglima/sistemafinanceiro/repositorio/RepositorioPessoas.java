package com.douglasproglima.sistemafinanceiro.repositorio;

import java.util.List;

import com.douglasproglima.sistemafinanceiro.model.Pessoa;

public interface RepositorioPessoas {
	public List<Pessoa> consultaTodas();
	public Pessoa consultaPorCodigo(Integer codigo);
}