package com.douglasproglima.sistemafinanceiro.util;

import java.io.Serializable;

import org.hibernate.Session;

import com.douglasproglima.sistemafinanceiro.repositorio.FichaDeLancamentos;
import com.douglasproglima.sistemafinanceiro.repositorio.FichaDePessoas;
import com.douglasproglima.sistemafinanceiro.repositorio.DAO.LancamentosDAO;
import com.douglasproglima.sistemafinanceiro.repositorio.DAO.PessoasDAO;

@SuppressWarnings("serial")
public class Repositorios implements Serializable{
	
	private Session getSessao(){
		//Atributo 'sessao' da class HibernateSessionFilter método doFilter()
		return (Session) FacesUtil.getAtributosDaRequisicao("sessaoMetodoDoFilter");
	}
	
	public FichaDePessoas getPessoas(){
		return new PessoasDAO(this.getSessao());
	}
	
	public FichaDeLancamentos getLancamentos(){
		return new LancamentosDAO(this.getSessao());
	}
}