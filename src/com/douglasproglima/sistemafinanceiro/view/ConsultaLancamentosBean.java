package com.douglasproglima.sistemafinanceiro.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.douglasproglima.sistemafinanceiro.model.Lancamento;
import com.douglasproglima.sistemafinanceiro.util.FacesUtil;

@ManagedBean
public class ConsultaLancamentosBean {
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();
	private Lancamento lancamentoSelecionado;
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void inicializar(){
		Session sessao = (Session) FacesUtil.getAtributosDaRequisicao("sessaoMetodoDoFilter");
		
		this.lancamentos = sessao.createCriteria(Lancamento.class).addOrder(Order.desc("dataVencimento")).list();
	}
	
	public void excluir(){
		if (this.lancamentoSelecionado.isPago()) {
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, 
										"O lançamento já foi pago. \nRegistro não pode ser excluído!", 
										"O lançamento já foi pago. \nRegistro não pode ser excluído!");
		}else{
			Session sessao = (Session) FacesUtil.getAtributosDaRequisicao("sessaoMetodoDoFilter");
			sessao.delete(this.lancamentoSelecionado);

			inicializar();
			
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, 
										"Lançamento Excluído com sucesso!", 
										"Lançamento Excluído com sucesso!");
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
