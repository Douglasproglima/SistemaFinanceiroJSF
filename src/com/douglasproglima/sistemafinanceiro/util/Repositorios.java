package com.douglasproglima.sistemafinanceiro.util;

import java.io.Serializable;

import org.hibernate.Session;

import com.douglasproglima.sistemafinanceiro.repositorio.RepositorioLancamentos;
import com.douglasproglima.sistemafinanceiro.repositorio.RepositorioPessoas;
import com.douglasproglima.sistemafinanceiro.repositorio.infra.LancamentosHibernate;
import com.douglasproglima.sistemafinanceiro.repositorio.infra.PessoasHibernate;

@SuppressWarnings("serial")
public class Repositorios implements Serializable{
	
	//Class responsável para não expor a class que implementa a 
	//interface (repositório) que será utilizado no managedBean
	
	private Session getSessao(){
		return null; //(Session) FacesUtil.getAtributosDaRequisicao("atributoSessaoDoFilter");
	}
	
	public RepositorioPessoas getPessoas(){
		return new PessoasHibernate(this.getSessao());
	}
	
	public RepositorioLancamentos getLancamentos(){
		return new LancamentosHibernate(this.getSessao());
	}
	
}
