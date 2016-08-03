package com.douglasproglima.sistemafinanceiro.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.douglasproglima.sistemafinanceiro.model.Pessoa;
import com.douglasproglima.sistemafinanceiro.repositorio.FichaDePessoas;
import com.douglasproglima.sistemafinanceiro.util.Repositorios;

//Este conversor não precisa espeficicar na página, porque estamos falando que ele irá sempre ser utilizado pela class pessoa
@FacesConverter(forClass=Pessoa.class)
public class ConversorPessoa implements Converter{

	private Repositorios repositorios = new Repositorios();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent componente, String valor) {
		Pessoa retornoPessoa = null;
		
		if(valor != null){
			FichaDePessoas fichaDePessoas = repositorios.getPessoas();
			
			retornoPessoa = fichaDePessoas.consultaPorCodigo(new Integer(valor));
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