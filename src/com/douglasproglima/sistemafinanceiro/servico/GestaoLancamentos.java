package com.douglasproglima.sistemafinanceiro.servico;

import com.douglasproglima.sistemafinanceiro.model.Lancamento;
import com.douglasproglima.sistemafinanceiro.repositorio.FichaDeLancamentos;

public class GestaoLancamentos {

	/*Boas Práticas
	 * Em uma camada de regra de negócio pode acessar API do JSF, exemplo FacesContext, FacesMessages etc...
	 * Não é aconselhavél colocar detalhes de API na class de negócio.
	 * */
	
	private FichaDeLancamentos fichaDeLancamentos;
	
	//Injeção de dependência pelo construtor.
	public GestaoLancamentos(FichaDeLancamentos fichaDeLancamentos){
		this.fichaDeLancamentos = fichaDeLancamentos;
	}

	private boolean existeLancamentoIgual(Lancamento lancamento){
		Lancamento lancamentoDuplicado = this.fichaDeLancamentos.comDadosIguais(lancamento);
		
		return  lancamentoDuplicado != null && !lancamentoDuplicado.equals(lancamento);
	}	
	
	public void salvar(Lancamento lancamento) throws RegraNegocioException{
		if (existeLancamentoIgual(lancamento)) {
			throw new RegraNegocioException("msg_lancamento_existente");
		}
		
		this.fichaDeLancamentos.guardar(lancamento);
	}
	
	public void excluir(Lancamento lancamento) throws RegraNegocioException{
		if (lancamento.isPago()) {
			throw new RegraNegocioException("msg_impedir_exclusao");
		} else {
			this.fichaDeLancamentos.remover(lancamento);
		}
	}
}