package com.douglasproglima.sistemafinanceiro.validadores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.sun.faces.util.MessageFactory;

@FacesValidator("com.douglasproglima.RequirimentoCondicional")
public class ValidacaoRequerimentoCondicional implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent componente, Object valor) throws ValidatorException {
		//Pega o atributo personalizado que foi criada na página CadastroLancamentos.xhtml
		Boolean checado = (Boolean) componente.getAttributes().get("atributoPersonalizadoChecado"); 
		
		if(checado != null && valor == null){
			Object label = MessageFactory.getLabel(context, componente);
			
			String msgErro = "Preencha o campo " + label + ".";
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErro.toString(), msgErro.toString());
			
			throw new ValidatorException(mensagem);
		}
	}

}
