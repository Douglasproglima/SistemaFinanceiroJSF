package com.douglasproglima.sistemafinanceiro.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.douglasproglima.sistemafinanceiro.model.Lancamento;
import com.douglasproglima.sistemafinanceiro.model.Pessoa;
import com.douglasproglima.sistemafinanceiro.model.TipoLancamento;
import com.douglasproglima.sistemafinanceiro.repositorio.FichaDePessoas;
import com.douglasproglima.sistemafinanceiro.servico.GestaoLancamentos;
import com.douglasproglima.sistemafinanceiro.servico.RegraNegocioException;
import com.douglasproglima.sistemafinanceiro.util.FacesUtil;
import com.douglasproglima.sistemafinanceiro.util.Repositorios;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CadastroLancamentosBean implements Serializable{
	
	private Repositorios repositorios = new Repositorios();
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	private Lancamento lancamento = new Lancamento();
	
	//Este método será chamado sempre que o managedBean for criado, por isso estou usando a anotação @PostConstruct
	@PostConstruct
	public void iniciar(){
		FichaDePessoas fichaDePessoas = this.repositorios.getPessoas();
		this.pessoas = fichaDePessoas.consultaTodas();	
	}	
	
	public void cadastrar() {
		GestaoLancamentos gestaoLancamentos = new GestaoLancamentos(this.repositorios.getLancamentos());
		try {
			gestaoLancamentos.salvar(lancamento);
			
			this.lancamento = new Lancamento(); //Limpa a tela
			
			String msg = "Cadastro efetuado com sucesso!";
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, msg, msg);
		} catch (RegraNegocioException erro) {
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, erro.getMessage(), erro.getMessage());
		}
	}
	
	//Este método será executado antes da validação referente ao ciclo de vida da requisição.
	//Foi necessário implementar este método pois foi utilizar o atributo emidiate no componente pago da página
	//Onde o mesmo será verificado através do campo data do pagamento.
	public void lancamentoPagoAlterado(ValueChangeEvent evt){
		
		this.lancamento.setPago((Boolean) evt.getNewValue());
		this.lancamento.setDataPagamento(null);
		
		//Força o JSF a pular para última fase do ciclo de vida da requisição que reinderizar
		FacesContext.getCurrentInstance().renderResponse();
	}

	public TipoLancamento[] getTiposLancamentos(){
		return TipoLancamento.values();
	}	
	
	public Lancamento getLancamento() {
		return lancamento;
	}
	
	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
}