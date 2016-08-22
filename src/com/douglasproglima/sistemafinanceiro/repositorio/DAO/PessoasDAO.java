package com.douglasproglima.sistemafinanceiro.repositorio.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.douglasproglima.sistemafinanceiro.model.Pessoa;
import com.douglasproglima.sistemafinanceiro.repositorio.FichaDePessoas;

public class PessoasDAO implements FichaDePessoas{
	private Session sessao;
	
	//A sessão será realizada pelo construtor, o que se chama de injeção de independência. 
	//Poderia ser realizado através do método setSessao().
	public PessoasDAO(Session sessao){
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

	@Override
	public Pessoa comDadosIguais(Pessoa pessoa) {
		
		return (Pessoa) this.sessao.createCriteria(Pessoa.class)
									     .add(Restrictions.eq("nome", pessoa.getNome()))
									     .add(Restrictions.eq("cpf", pessoa.getCpf()))
									     .uniqueResult();
	}

	@Override
	public Pessoa guardar(Pessoa pessoa) {
		return (Pessoa) sessao.merge(pessoa);
	}

	@Override
	public void remover(Pessoa pessoa) {
		this.sessao.delete(pessoa);	
	}
}