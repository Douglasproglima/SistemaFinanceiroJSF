package com.douglasproglima.sistemafinanceiro.validadores;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.douglasproglima.sistemafinanceiro.util.FacesUtil;
import com.sun.faces.util.MessageFactory;

@FacesValidator("com.douglasproglima.DataFutura")
public class ValidarDataFutura implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent componente, Object valor) throws ValidatorException {
		Date data = (Date) valor;
		
		if(data != null && data.after(new Date())){
			Object label = MessageFactory.getLabel(context, componente); //Retorna o nome do label configurado na página
			
			String descricaoErro = label + " " + FacesUtil.getMensagemI18N("msg_data_futura");
			
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, descricaoErro, descricaoErro);
			
			throw new ValidatorException(mensagem);
		}
	}
	
}
