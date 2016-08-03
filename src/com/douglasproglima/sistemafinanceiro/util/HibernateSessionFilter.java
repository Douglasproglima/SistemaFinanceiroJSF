package com.douglasproglima.sistemafinanceiro.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.hibernate.Session;
import org.hibernate.Transaction;

//Criação do interceptador para o faces servlet, antes e depois do faces servlet ser executado, será criado um filtro
@WebFilter(servletNames={"Faces Servlet"})
public class HibernateSessionFilter implements Filter{

	@Override
	public void doFilter(ServletRequest requisicao, ServletResponse resposta, FilterChain chain)
			throws IOException, ServletException {
		
		Session sessao =  HibernateUtil.getSessao();
		Transaction transacao = null;
		
		try {
			//Inicia uma transação com o banco de dados;
			transacao = sessao.beginTransaction();
			
			//Atribui a requisiçao a sessão do hibernate, será utilizado nos managesBean nas anotações de escopos.
			requisicao.setAttribute("sessaoMetodoDoFilter", sessao);
			
			//Continua o processo depois da requisição
			chain.doFilter(requisicao, resposta);
			
			transacao.commit();
		} catch (Exception erro) {
			if (transacao != null) {
				transacao.rollback();
			}	
		}finally {
			sessao.close();
		}
	}	
	
	@Override
	public void destroy() {	
	}

	@Override
	public void init(FilterConfig coniguracaoFiltro) throws ServletException {
	}
}
