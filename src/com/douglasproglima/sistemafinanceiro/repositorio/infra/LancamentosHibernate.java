package com.douglasproglima.sistemafinanceiro.repositorio.infra;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.douglasproglima.sistemafinanceiro.model.Lancamento;
import com.douglasproglima.sistemafinanceiro.repositorio.RepositorioLancamentos;

public class LancamentosHibernate implements RepositorioLancamentos{

	private Session sessao;
	
	public LancamentosHibernate(Session sessao){
		this.sessao = sessao;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Lancamento> consultaTodas() {
		return sessao.createCriteria(Lancamento.class).addOrder(Order.desc("dataVencimento")).list();
	}

	@Override
	public Lancamento salvar(Lancamento lancamento) {
		return (Lancamento) sessao.merge(lancamento);
	}

	@Override
	public void remover(Lancamento lancamento) {
		this.sessao.delete(lancamento);
	}
}