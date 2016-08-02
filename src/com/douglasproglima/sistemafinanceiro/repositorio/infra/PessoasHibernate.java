package com.douglasproglima.sistemafinanceiro.repositorio.infra;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.douglasproglima.sistemafinanceiro.model.Pessoa;
import com.douglasproglima.sistemafinanceiro.repositorio.RepositorioPessoas;

public class PessoasHibernate implements RepositorioPessoas{
	private Session sessao;
	
	//Recebe a sessão pelo construtor (Poderia fazer pelo setSessao()), em resumo por injeção de dependência
	public PessoasHibernate(Session sessao){
		this.sessao = sessao;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pessoa> consultaTodas() {
		
		return sessao.createCriteria(Pessoa.class).addOrder(Order.asc("nome")).list();
	}

	@Override
	public Pessoa consultaPorCodigo(Integer codigo) {

		return (Pessoa) sessao.get(Pessoa.class, new Integer(codigo));
	}

}
