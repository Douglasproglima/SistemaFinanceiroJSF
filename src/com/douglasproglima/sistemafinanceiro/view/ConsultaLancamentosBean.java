package com.douglasproglima.sistemafinanceiro.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.douglasproglima.sistemafinanceiro.model.Lancamento;
import com.douglasproglima.sistemafinanceiro.util.HibernateUtil;

@ManagedBean
public class ConsultaLancamentosBean {
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@PostConstruct
	public void inicializar(){
		Session sessao = new HibernateUtil().getSessao();
		
		this.lancamentos = sessao.createCriteria(Lancamento.class).addOrder(Order.desc("dataVencimento")).list();
		
		sessao.close();
	}
	
	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
}
