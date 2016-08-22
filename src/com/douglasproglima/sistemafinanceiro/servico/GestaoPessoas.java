package com.douglasproglima.sistemafinanceiro.servico;

import com.douglasproglima.sistemafinanceiro.model.Pessoa;
import com.douglasproglima.sistemafinanceiro.repositorio.FichaDePessoas;

public class GestaoPessoas {

	private FichaDePessoas fichaDePessoas;
	
	public GestaoPessoas(FichaDePessoas fichaDePessoas){
		this.fichaDePessoas = fichaDePessoas;
	}
	
	private boolean existePessoasIguais(Pessoa pessoa){
		Pessoa pessoaDuplicada = this.fichaDePessoas.comDadosIguais(pessoa);
		
		return pessoaDuplicada != null && !pessoaDuplicada.equals(pessoa);
	}
	
	public void salvar(Pessoa pessoa) throws RegraNegocioException{
		if(existePessoasIguais(pessoa)){
			throw new RegraNegocioException("msg_lancamento_existente");
		}
		
		this.fichaDePessoas.guardar(pessoa);
	}
	
	public void excluir(Pessoa pessoa){
		this.fichaDePessoas.remover(pessoa);
	}
}
