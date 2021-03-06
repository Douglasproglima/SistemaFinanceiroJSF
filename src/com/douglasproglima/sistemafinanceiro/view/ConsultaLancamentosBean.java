package com.douglasproglima.sistemafinanceiro.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import com.douglasproglima.sistemafinanceiro.model.Lancamento;
import com.douglasproglima.sistemafinanceiro.repositorio.FichaDeLancamentos;
import com.douglasproglima.sistemafinanceiro.servico.GestaoLancamentos;
import com.douglasproglima.sistemafinanceiro.servico.RegraNegocioException;
import com.douglasproglima.sistemafinanceiro.util.FacesUtil;
import com.douglasproglima.sistemafinanceiro.util.Repositorios;

@ManagedBean
public class ConsultaLancamentosBean {
	
	private Repositorios repositorios = new Repositorios();
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();
	private Lancamento lancamentoSelecionado;
	
	@PostConstruct
	public void iniciar(){
		FichaDeLancamentos fichaDeLancamentos = repositorios.getLancamentos();
		this.lancamentos = fichaDeLancamentos.consultaTodos();
	}
	
	public void excluir(){
		GestaoLancamentos gestaoLancamentos = new GestaoLancamentos(this.repositorios.getLancamentos());
		try {
			gestaoLancamentos.excluir( this.lancamentoSelecionado);
			
			this.iniciar();
			
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, 
										FacesUtil.getMensagemI18N("msg_exclusao"), 
										FacesUtil.getMensagemI18N("msg_exclusao"));
		} catch (RegraNegocioException erro) {
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, 
										FacesUtil.getMensagemI18N(erro.getMessage()), 
										FacesUtil.getMensagemI18N(erro.getMessage()));
		}
		
//		if (this.lancamentoSelecionado.isPago()) {
//			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, 
//										"O lançamento já foi pago. \nRegistro não pode ser excluído!", 
//										"O lançamento já foi pago. \nRegistro não pode ser excluído!");
//		}else{
//			FichaDeLancamentos fichaDeLancamentos = repositorios.getLancamentos();
//			fichaDeLancamentos.remover(this.lancamentoSelecionado);
//			
//			iniciar();
//			
//			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, 
//										"Lançamento Excluído com sucesso!", 
//										"Lançamento Excluído com sucesso!");
//		}
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