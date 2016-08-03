package com.douglasproglima.sistemafinanceiro.repositorio.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.douglasproglima.sistemafinanceiro.model.Lancamento;
import com.douglasproglima.sistemafinanceiro.repositorio.FichaDeLancamentos;

public class LancamentosDAO implements FichaDeLancamentos{
	
	private Session sessao;
	
	public LancamentosDAO(Session sessao) {
		this.sessao = sessao;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Lancamento> consultaTodos() {
		return sessao.createCriteria(Lancamento.class).addOrder(Order.desc("dataVencimento")).list();
	}

	@Override
	public Lancamento guardar(Lancamento lancamento) {
		return (Lancamento) sessao.merge(lancamento);
	}

	@Override
	public void remover(Lancamento lancamento) {
		this.sessao.delete(lancamento);
	}

}