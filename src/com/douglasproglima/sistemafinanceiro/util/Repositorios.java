package com.douglasproglima.sistemafinanceiro.util;

import java.io.Serializable;

import org.hibernate.Session;

import com.douglasproglima.sistemafinanceiro.repositorio.FichaDePessoas;
import com.douglasproglima.sistemafinanceiro.repositorio.DAO.PessoasDAO;

public class Repositorios implements Serializable{
	
	private Session getSessao(){
		return (Session) FacesUtil.getAtributosDaRequisicao("sessaoMetodoDoFilter");
	}
	
	public FichaDePessoas getPessoas(){
		return new PessoasDAO(this.getSessao());
	}
}