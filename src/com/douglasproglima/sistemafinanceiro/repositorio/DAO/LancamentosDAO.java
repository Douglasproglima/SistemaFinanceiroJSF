package com.douglasproglima.sistemafinanceiro.repositorio.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

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
	public Lancamento porCodigo(Integer codigo) {
		return (Lancamento) sessao.get(Lancamento.class, new Integer(codigo));
	}
	
	@Override
	public Lancamento guardar(Lancamento lancamento) {
		return (Lancamento) sessao.merge(lancamento);
	}

	@Override
	public void remover(Lancamento lancamento) {
		this.sessao.delete(lancamento);
	}

	@Override
	public Lancamento comDadosIguais(Lancamento lancamento) {
		return (Lancamento) this.sessao.createCriteria(Lancamento.class)
						  			   .add(Restrictions.eq("tipo", lancamento.getTipo()))
						  			   .add(Restrictions.eq("pessoa", lancamento.getPessoa()))
						  			   .add(Restrictions.ilike("descricao", lancamento.getDescricao()))
						  			   .add(Restrictions.eq("valor", lancamento.getValor()))
						  			   .add(Restrictions.eq("dataVencimento", lancamento.getDataVencimento()))
						  			   .uniqueResult();
	}
}