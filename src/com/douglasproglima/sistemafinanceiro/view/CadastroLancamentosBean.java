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
	
	public void salvar() {
		GestaoLancamentos gestaoLancamentos = new GestaoLancamentos(this.repositorios.getLancamentos());
		try {
			gestaoLancamentos.salvar(lancamento);
			
			this.lancamento = new Lancamento(); //Limpa a tela
			
			String msg = FacesUtil.getMensagemI18N("msg_lancamento_salvo");
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, msg, msg);
		} catch (RegraNegocioException erro) {
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, 
										FacesUtil.getMensagemI18N(erro.getMessage()), 
										FacesUtil.getMensagemI18N(erro.getMessage()));
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
	
	//Método utilizar para alterar o nome da página de forma dinâmica
	public boolean isEditar(){
		//Se o código não for null o usuário está editando o registro
		return this.lancamento.getCodigo() != null;
	}

	public TipoLancamento[] getTiposLancamentos(){
		return TipoLancamento.values();
	}	
	
	public Lancamento getLancamento() {
		return lancamento;
	}
	
	public void setLancamento(Lancamento lancamento) throws CloneNotSupportedException {
		this.lancamento = lancamento;
		
		//No de um novo registro o lançamento será null, para evitar o 
		//erro de pointExceptio instânciado um novo lançamento
		if (this.lancamento == null) {
			this.lancamento = new Lancamento();
		}else{
			//Caso o usuário altere o valor de um registro obtendo o mesmo valor de um registro já inserido no banco
			//ele obtem o mesmo comportamento da regra de negócio que impede o usuário de inserir registros duplicados
			this.lancamento =  (Lancamento) lancamento.clone();
		}
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
}