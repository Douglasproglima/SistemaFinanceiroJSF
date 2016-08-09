package com.douglasproglima.sistemafinanceiro.view;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class AjaxBean {
	private String descricao;

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}