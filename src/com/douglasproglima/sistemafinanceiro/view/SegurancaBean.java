package com.douglasproglima.sistemafinanceiro.view;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.douglasproglima.sistemafinanceiro.util.FacesUtil;

public class SegurancaBean {
	
	private String usuario;
	private String senha;
	
	//Método de logar
	public String logar(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		//Busca o http do Servlet
		HttpServletRequest requisicao = (HttpServletRequest) context.getExternalContext().getRequest();
		
		try {
			requisicao.login(usuario, senha);			
			return "PaiginaInicial?faces=redirect=true";
		} catch (Exception e) {
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, 
										FacesUtil.getMensagemI18N("msg_user_senha_nao_existem"), 
										FacesUtil.getMensagemI18N("msg_user_senha_nao_existem"));
			return null;
		}
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}