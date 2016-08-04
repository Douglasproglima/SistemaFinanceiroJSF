package com.douglasproglima.sistemafinanceiro.conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.douglasproglima.sistemafinanceiro.model.Lancamento;
import com.douglasproglima.sistemafinanceiro.repositorio.FichaDeLancamentos;
import com.douglasproglima.sistemafinanceiro.util.Repositorios;

//Foi declarado que este conversor será utilizado específicamente para a class Lancamento
//Na meta tag da página automáticamente será utilizado este conversor.
@FacesConverter(forClass=Lancamento.class)
public class ConversorLancamento implements Converter{

	private Repositorios repositorios = new Repositorios();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent componente, String valor) {
		Lancamento retornoLancamento = null;
		FichaDeLancamentos fichaDeLancamentos = this.repositorios.getLancamentos();
		
		if(valor != null && !valor.equals("")){
			retornoLancamento = fichaDeLancamentos.consultarPorCodigo(new Integer(valor));
			
			//Quando for converter de String para Objeto o lançamento não existir será lançado uma msg p/o user.
			if (retornoLancamento == null) {
				String msgErro = "Registro de lançamento não existe.";
				FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErro, msgErro);
				throw new ConverterException(mensagem);
			}
		}
		
		return retornoLancamento;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent componente, Object valor) {
		if (valor != null) {
			Integer codigo = ((Lancamento) valor).getCodigo();
			return codigo == null ? "" : codigo.toString();
		}
		
		return null;
	}
}