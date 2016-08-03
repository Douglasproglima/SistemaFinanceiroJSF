package com.douglasproglima.sistemafinanceiro.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.hibernate.Session;

import com.douglasproglima.sistemafinanceiro.model.Pessoa;
import com.douglasproglima.sistemafinanceiro.util.FacesUtil;

//Este conversor não precisa espeficicar na página, porque estamos falando que ele irá sempre ser utilizado pela class pessoa
@FacesConverter(forClass=Pessoa.class)
public class ConversorPessoa implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent componente, String valor) {
		Pessoa retornoPessoa = null;
		
		if(valor != null){
			Session sessao = (Session) FacesUtil.getAtributosDaRequisicao("sessaoMetodoDoFilter");

			retornoPessoa = (Pessoa) sessao.get(Pessoa.class, new Integer(valor));
		}
		
		return retornoPessoa;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent componente, Object valor) {
		if (valor != null) {
			return ((Pessoa) valor).getCodigo().toString();	
		}
		
		return null;
	}
}