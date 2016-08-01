package com.douglasproglima.sistemafinanceiro.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import com.douglasproglima.sistemafinanceiro.model.Lancamento;
import com.douglasproglima.sistemafinanceiro.util.FacesUtil;
import com.douglasproglima.sistemafinanceiro.util.HibernateUtil;

@ManagedBean
public class ConsultaLancamentosBean {
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();
	private Lancamento lancamentoSelecionado;
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@PostConstruct
	public void inicializar(){
		Session sessao = new HibernateUtil().getSessao();
		
		this.lancamentos = sessao.createCriteria(Lancamento.class).addOrder(Order.desc("dataVencimento")).list();
		
		sessao.close();
	}
	
	public void excluir(){
		if (this.lancamentoSelecionado.isPago()) {
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, 
										"O lançamento já foi pago. \nRegistro não pode ser excluído!", 
										"O lançamento já foi pago. \nRegistro não pode ser excluído!");
		}else{
			Session sessao = HibernateUtil.getSessao();
			Transaction transacao = sessao.beginTransaction();
			
			sessao.delete(this.lancamentoSelecionado);
			
			transacao.commit();
			sessao.close();
			
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, 
										"Lançamento Excluído com sucesso!", 
										"Lançamento Excluído com sucesso!");
			
			inicializar();
		}
	}
	
	//Métodos Bean Getters e Setters
	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
	
	public Lancamento getLancamentoSelecionado() {
		return lancamentoSelecionado;
	}
	public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}
}
