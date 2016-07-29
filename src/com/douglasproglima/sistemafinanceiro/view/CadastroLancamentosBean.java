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

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.douglasproglima.sistemafinanceiro.model.Lancamento;
import com.douglasproglima.sistemafinanceiro.model.Pessoa;
import com.douglasproglima.sistemafinanceiro.model.TipoLancamento;
//import com.douglasproglima.sistemafinanceiro.service.GestaoPessoas;
import com.douglasproglima.sistemafinanceiro.util.HibernateUtil;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CadastroLancamentosBean implements Serializable{
	
	public Lancamento lancamento = new Lancamento();
	public List<Pessoa> pessoas = new ArrayList<Pessoa>();

	//Este será chamado sempre que o managedBean for criado, por isso estou usando a anotação @PostConstruct
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void iniciar(){
//		GestaoPessoas gestaoPessoas = new GestaoPessoas();
//		this.pessoas = gestaoPessoas.exibirTodasPessoas();
		
		Session sessao = HibernateUtil.getSessao();
		
		this.pessoas = sessao.createCriteria(Pessoa.class).addOrder(Order.asc("nome")).list();
		
		sessao.close();		
	}	
	
	public void cadastrar() {
		System.out.println("Tipo: " + this.lancamento.getTipo());
		System.out.println("Pessoa: " + this.lancamento.getPessoa().getNome());
		System.out.println("Descrição: " + this.lancamento.getDescricao());
		System.out.println("Valor: " + this.lancamento.getValor());
		System.out.println("Data vencimento: " + this.lancamento.getDataVencimento());
		System.out.println("Conta paga: " + this.lancamento.isPago());
		System.out.println("Data pagamento: " + this.lancamento.getDataPagamento());

		this.lancamento = new Lancamento(); //Limpa a tela
		
		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
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
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
}