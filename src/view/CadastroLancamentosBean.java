package view;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Lancamento;
import model.TipoLancamento;

@ManagedBean
@ViewScoped
public class CadastroLancamentosBean implements Serializable{
	
	public Lancamento lancamento = new Lancamento();
	
	public void cadastrar() {
		System.out.println("Tipo: " + this.lancamento.getTipo());
		//System.out.println("Pessoa: " + this.lancamento.getPessoa().getNome());
		System.out.println("Descrição: " + this.lancamento.getDescricao());
		System.out.println("Valor: " + this.lancamento.getValor());
		System.out.println("Data vencimento: " + this.lancamento.getDataVencimento());
		System.out.println("Conta paga: " + this.lancamento.isPago());
		System.out.println("Data pagamento: " + this.lancamento.getDataPagamento());

		this.lancamento = new Lancamento(); //Limpa a tela
		
		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}
	
	public TipoLancamento[] getTiposLancamentos(){
		return TipoLancamento.values();
	}
	
	public Lancamento getLancamento() {
		return lancamento;
	}
}