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
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import com.douglasproglima.sistemafinanceiro.model.Lancamento;
import com.douglasproglima.sistemafinanceiro.model.Pessoa;
import com.douglasproglima.sistemafinanceiro.model.TipoLancamento;
import com.douglasproglima.sistemafinanceiro.util.FacesUtil;
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
		Session sessao = HibernateUtil.getSessao();
		
		this.pessoas = sessao.createCriteria(Pessoa.class).addOrder(Order.asc("nome")).list();
		
		sessao.close();		
	}	
	
	@SuppressWarnings("static-access")
	public void cadastrar() {
		Session sessao = new HibernateUtil().getSessao();
		
		Transaction transacao = sessao.beginTransaction();
		
		sessao.merge(this.lancamento);

		transacao.commit();
		sessao.close();
		
		this.lancamento = new Lancamento(); //Limpa a tela
		
		String msg = "Cadastro efetuado com sucesso!";
		FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, msg, msg);
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