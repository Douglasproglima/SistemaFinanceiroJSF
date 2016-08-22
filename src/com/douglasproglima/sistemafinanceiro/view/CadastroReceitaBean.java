package com.douglasproglima.sistemafinanceiro.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.douglasproglima.sistemafinanceiro.model.Pessoa;
import com.douglasproglima.sistemafinanceiro.servico.GestaoPessoas;
import com.douglasproglima.sistemafinanceiro.util.FacesUtil;
import com.douglasproglima.sistemafinanceiro.util.Repositorios;

@ManagedBean
@ViewScoped
public class CadastroReceitaBean implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Pessoa pessoa = new Pessoa();
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();

	public void salvar(){
		try {
			GestaoPessoas gestaoPessoas = new GestaoPessoas(this.repositorios.getPessoas());
			
			gestaoPessoas.salvar(pessoa);
			
			this.pessoa = new Pessoa();
			
			String msg = FacesUtil.getMensagemI18N("msg_lancamento_salvo");
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, msg, msg);			
		} catch (Exception e) {
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, 
										FacesUtil.getMensagemI18N(e.getMessage()), 
										FacesUtil.getMensagemI18N(e.getMessage()));
		}
	}
	
	public boolean isEditar(){
		return this.pessoa.getCodigo() != null;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
}
