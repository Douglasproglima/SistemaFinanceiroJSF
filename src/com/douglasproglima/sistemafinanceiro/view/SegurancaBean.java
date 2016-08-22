package com.douglasproglima.sistemafinanceiro.view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.douglasproglima.sistemafinanceiro.util.FacesUtil;

@ManagedBean
public class SegurancaBean {
	
	private String usuario;
	private String senha;

	//Busca o http do ServletRequest
	private HttpServletRequest getRequisicao(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		return (HttpServletRequest) context.getExternalContext().getRequest();
	}	
	
	//Método de logar
	public String logar(){
		
		try {
			this.getRequisicao().login(usuario, senha);			
			return "PaginaInicial?faces=redirect=true";
		} catch (Exception e) {
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, 
										FacesUtil.getMensagemI18N("msg_user_senha_nao_existem"), 
										FacesUtil.getMensagemI18N("msg_user_senha_nao_existem"));
			return null;
		}
	}
	
	public String sair() throws ServletException{
		
		this.getRequisicao().logout();
		return "Login?faces=redirect=true";
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